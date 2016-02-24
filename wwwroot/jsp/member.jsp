<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.main.bean.MemberVO"%>
<%@page import="com.pmanage.framework.data.DataSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<%
	DataSet data = (DataSet)request.getAttribute("membersNative");
%>
<head></head>
 <body>
<!--  el 기능. -->
 단순조회 : ${members[0].userId}
<br/>
 조인조회 : ${memberHistoryJoin[0].userId} + ${memberHistoryJoin[0].loginGubun}
<br/>
 API조회 : ${membersCriteria[0].userId}
 <br/>
 Native조회 : 
 <br/>
 
 <%while(data.next()){ %>
 <%=data.getString("USER_ID")%>+<%=data.getString("NAME")%><br/>
 <%} %>
</body>
</html>

