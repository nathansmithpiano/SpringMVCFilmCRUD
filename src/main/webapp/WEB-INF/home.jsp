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
	<!-- View Film Test -->
	<hr>
	<h3>View Film</h3>
	<form action="displayFilm.do">
		<label for="fname">Film id:</label>
		<input type="number" id="filmid" name="filmid">
		<input type="submit" value="Submit">
	</form>
	<hr>

	<!-- Remove Film Test -->
	<hr>
	<h3>Remove Film</h3>
	<form action="removeFilm.do">
		<label for="id">Film id:</label>
		<input type="text" id="filmid" name="filmid"><br>
		<input type="submit" value="Submit">
	</form>
	<hr>
</body>
</html>