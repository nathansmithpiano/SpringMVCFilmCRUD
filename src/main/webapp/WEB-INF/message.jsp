<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test='${params.status ==  "remove"}'>
			<h4>Film Removed</h4>
			<p>ID: ${params.filmDeleted.id }</p>
		</c:when>
	</c:choose>

</body>
</html>