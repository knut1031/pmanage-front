<%@page import="com.main.bean.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>

<head></head>
<body>
<!--  el 기능. -->
${members[0].name}
<br/>
${members[1].name}
<br/>
${members[2].name}
</body>
</html>

