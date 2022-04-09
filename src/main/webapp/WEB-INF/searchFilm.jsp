<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Search Film</h2>
	
	<h3>Search by ID</h3>
	<form action="displayFilm.do">
		<label for="fname">Film id to be displayed:</label>
		<input type="text"id="filmid" name="filmid">
		<br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>