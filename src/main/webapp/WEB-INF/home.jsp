<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Film Site 2</h1>
	
	<a href="goSearchFilm.do">Search Film By ID</a>
	<br>
	<a href="goAddFilm.do">Add New Film</a>
	<br>
	<br>
	<br>


	<!-- Remove Film Test -->
	<form action="removeFilm.do">
		<label for="fname">Film to Remove id:</label>
		<input type="text"id="filmid" name="filmid">
		 <input type="submit" value="Submit">
	</form>
	
	<form action="displayFilm.do">
		<label for="fname">Film id to be displayed:</label>
		<input type="text"id="filmid" name="filmid"><br>
		 <input type="submit" value="Submit">
	</form>
	
</body>
</html>