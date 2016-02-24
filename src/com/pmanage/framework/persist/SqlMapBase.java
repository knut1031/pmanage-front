package com.pmanage.framework.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.pmanage.framework.data.DataSet;

public class SqlMapBase {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<?> querySelect(Class cls, String path, String name, Map map ){
		Session session = sessionFactory.getCurrentSession();
		
		// HQL
		String hql = loadQuery(path, name);
		Query query = session.createQuery(hql);
		
		
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String param = (String) iter.next();
			if(hql.indexOf(":"+param) >= 0){
				query.setParameter(param,map.get(param));
			}
		}
		
		//List<UserLoginHistoryVO> result = (List<UserLoginHistoryVO>) query.list();
		// 위와같이 했을 경우 object 배열을 반환하므로 jsp 에서 매우 귀찮게 된다. 
		// 그러므로 아래 소스를 추가한다.
		// 단, select 절에 alias 가 반드시 있어야 한다.
		List<?> result =  (List<?>) query.setResultTransformer(Transformers.aliasToBean(cls)).list();
	
		return result;
	}
	
	public List<?> queryList(Class cls, String path, String name, Map map ){
		Session session = sessionFactory.getCurrentSession();
		
		// HQL
		String hql = loadQuery(path, name);
		Query query = session.createQuery(hql);
		
		
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String param = (String) iter.next();
			if(hql.indexOf(":"+param) >= 0){
				query.setParameter(param,map.get(param));
			}
		}
		
		List<?> result =  (List<?>) query.list();
	
		return result;
	}
	
	public DataSet queryNative(String path, String name, Map map ){
		Session session = sessionFactory.getCurrentSession();
		
		String sql = loadQuery(path, name);
		SQLQuery query = session.createSQLQuery(sql);
		
		
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String param = (String) iter.next();
			if(sql.indexOf(":"+param) >= 0){
				query.setParameter(param,map.get(param));
			}
		}
		
		DataSet resultDs = new DataSet();
		resultDs.setDataSet(	(ArrayList<HashMap>)query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list());
	
		return resultDs;
	}
	
	private String loadQuery(String sqlFile, String sqlName)  {
		 
		String query = "";
		
		URL url1 = (new DummyClass()).getClass().getResource("SqlMap.class");
		String path = url1.getFile();
		
		sqlFile = path = path.substring(0, path.indexOf("/classes"))+"/classes/sql/" + sqlFile;
		
		File file;
		InputStreamReader in = null; 
		
		try{
			
			// sqlFile 전체 글 읽어온다.
			file = new File(sqlFile);
			in = new InputStreamReader(new FileInputStream(file), "utf-8");
			
			int size = (int)file.length();
			char data[] = new char[size];
			int read = in.read(data, 0, size);
			
			query = new String(data, 0, read);
			
			// 해당 쿼리 검색한다.
			String queryName = "---- "+sqlName+" @";
			int inx1 = query.indexOf(queryName);
			if(inx1 < 0){
				return "";
			}
			
			int inx2 = query.indexOf("---- ",inx1+5);
			if(inx2 >= 0){
				query = query.substring(inx1, inx2).trim();
			}else{
				query = query.substring(inx1).trim();
			}
			
			// 주석삭제
			query = removeComment(query);
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return query;
	}
	
	// '---- ','-- ' 삭제 처리한다.
	private  String removeComment(String query){
		
		int iny1 = query.indexOf("---- ");
		if(iny1 >= 0){
			int iny2 = query.indexOf("\n",iny1);
			query = query.substring(iny2);
		}
		
		int inx1 = 0;
		int inx2 = 0;
		inx1 = query.indexOf("-- ");
		if(inx1 >= 0){
			do{
				inx2 = query.indexOf("\n",inx1);
				
				// 마지막줄에 주석들어갔을 경우 처리
				if(inx2 < 0){
					query = query.substring(0,inx1);
				}else{
					query = query.substring(0,inx1) + query.substring(inx2);
				}
				
				inx1 = query.indexOf("-- ");
			}while(inx1 >= 0);
		}
		
		return query;
	}
	
}
