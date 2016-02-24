package com.pmanage.framework.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.pmanage.framework.util.StringUtil;

public class DataSet{
	
	private	ArrayList	 dsLst			= null;
	private	HashMap	 hmap			= null;
	
	private	int		lstPos				=	-1;  // 출력할 행
	private	int		size				=	0; // 전체 사이즈
	
	public DataSet() { }
	
	public DataSet (ArrayList list){
		dsLst = list;
	}
	
	public void setDataSet(ArrayList llist) {
		dsLst =	llist;
		size = dsLst.size();
	}
	
	// 순차적으로 이동
	public boolean next(){
		if((lstPos + 1) < size){
			lstPos += 1;
			return true;
		}else{
			return false;
		}
	}
	
	public String getString(String colName){
		try{
			hmap = (HashMap)dsLst.get(lstPos);
			return StringUtil.nvl(   String.valueOf(hmap.get(colName.toUpperCase()))   );
		}catch (NullPointerException e) {
			return "";
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
