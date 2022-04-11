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

	<h3>Add Film</h3>
	<form id="filmForm" action="addFilm.do" method="POST">
		<!-- Title -->
		<label for="title">Title:</label>
		<input type="text" id="title" name="title" size="30" required>
		<!-- Description -->
		<br>
		<label for="description">Description:</label>
		<input type="text" name="description" size="30">
		<!-- Release Year -->
		<br>
		<label for="releaseYear">Release Year:</label>
		<input type="number" id="releaseYear" name="releaseYear" min="1895" max="2022">
		<!-- Language -->
		<br>
		<p>Language:</p>
		<input type="radio" id="english" name="languageId" value="1" checked>
		<label for="english">English</label>
		<br>
		<input type="radio" id="italian" name="languageId" value="2">
		<label for="italian">Italian</label>
		<br>
		<input type="radio" id="japanese" name="languageId" value="3">
		<label for="japanese">Japanese</label>
		<br>
		<input type="radio" id="mandarin" name="languageId" value="4">
		<label for="mandarin">Mandarin</label>
		<br>
		<input type="radio" id="french" name="languageId" value="5">
		<label for="french">French</label>
		<br>
		<input type="radio" id="german" name="languageId" value="6">
		<label for="german">German</label>
		<!-- Rental Duration -->
		<br>
		<label for="rentalDuration">Rental Duration (days)</label>
		<input type="range" id="rentalDuration" name="rentalDuration" min="1" max="7" value="3" required>
		Days: <span id="rentalDays"></span>
		<!-- Rental Rate -->
		<br>
		<label for="rental_rate">Rental Rate</label>
		<input type="number" name="rental_rate" step="0.01" size="6" min="0">
		<!-- Length -->
		<br>
		<label for="length">Length (min):</label>
		<input type="number" name="length" min="0">
		<!-- Replacement Cost -->
		<br>
		<label for="replacementCost">Replacement Cost</label>
		<input type="number" name="replacementCost" step="0.01" size="6">
		<!-- Rating -->
		<br>
		<p>Rating:</p>
		<input type="radio" id="G" name="rating" value="G" checked>
		<label for="G">G</label>
		<br>
		<input type="radio" id="PG" name="rating" value="PG">
		<label for="PG">PG</label>
		<br>
		<input type="radio" id="PG13" name="rating" value="PG13">
		<label for="PG13">PG13</label>
		<br>
		<input type="radio" id="R" name="rating" value="R">
		<label for="R">R</label>
		<br>
		<input type="radio" id="NC17" name="rating" value="NC17">
		<label for="NC17">NC17</label>
		
		<!-- Special Features -->
		<p>Special Features:</p>
		<input type="checkbox" id="trailers" name="sf" value="Trailers">
		<label for="trailers">Trailers</label>
		<br>
		<input type="checkbox" id="commentaries" name="sf" value="Commentaries">
		<label for="commentaries">Commentaries</label>
		<br>
		<input type="checkbox" id="deletedScenes" name="sf" value="Deleted Scenes">
		<label for="deletedScenes">Deleted Scenes</label>
		<br>
		<input type="checkbox" id="behindTheScenes" name="sf" value="Behind The Scenes">
		<label for="behindTheScenes">Behind The Scenes</label>
		<br>
		<hr>
		<input type="submit" value="Add Film">
	</form>
	
	<script>
	/* Rental Duration Slider */
		var slider = document.getElementById("rentalDuration");
		var output = document.getElementById("rentalDays");
		output.innerHTML = slider.value;
		slider.oninput = function() {
			output.innerHTML = this.value;
		}
	</script>
	

</body>
</html>