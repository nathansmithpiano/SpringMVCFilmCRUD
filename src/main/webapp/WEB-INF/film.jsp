<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Film title : ${film.title}</h1> 
	
	<c:choose>
		<c:when test="${empty film }">
			<h4>FILM NOT FOUND</h4>
		</c:when>
		<c:otherwise>
			<!-- new stuff -->
			<h3>Film Details</h3>
			<form action="addFilm.do" method="GET">
				<!-- Title -->
				<label for="title">Title:</label>
				<input type="text" name="title" size="30" value="${film.title}">
				<!-- Description -->
				<br>
				<label for="description">Description:</label>
				<input type="text" name="description" size="30" value="${film.description}">
				<!-- Release Year -->
				<br>
				<label for="releaseYear">Release Year:</label>
				<input type="number" name="releaseYear" min="1895" max="2022" value="${film.releaseYear}">
				<!-- Language -->
				<br>
				<p>Language:</p>
				<input type="radio" id="english" name="language" value="1">
				<label for="english">English</label>
				<br>
				<input type="radio" id="italian" name="language" value="2">
				<label for="italian">Italian</label>
				<br>
				<input type="radio" id="japanese" name="language" value="3">
				<label for="japanese">Japanese</label>
				<br>
				<input type="radio" id="mandarin" name="language" value="4">
				<label for="mandarin">Mandarin</label>
				<br>
				<input type="radio" id="french" name="language" value="5">
				<label for="french">French</label>
				<br>
				<input type="radio" id="german" name="language" value="6">
				<label for="german">German</label>
				
				<!-- Rental Duration -->
				<br>
				<label for="rentalDuration">Rental Duration (days)</label>
				<input type="range" id="rentalDuration" name="rentalDuration" min="1" max="7" value="4">
				Days: <span id="rentalDays"></span>
				
				<!-- Rental Rate -->
				<br>
				<label for="rental_rate">Rental Rate</label>
				<input type="text" name="rental_rate" size="6" value="${film.rental_rate}">
				<!-- Length -->
				<br>
				<label for="length">Length (min):</label>
				<input type="number" name="length" min="0" max="300" value="${film.length}">
				<!-- Replacement Cost -->
				<br>
				<label for="replacementCost">Replacement Cost</label>
				<input type="text" name="replacementCost" size="6" value="${film.replacementCost}">
				<!-- Rating -->
				<br>
				<p>Rating:</p>
				<input type="radio" id="G" name="rating" value="G">
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
				<input type="text" id="test" name="test" size=120 value="default">
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
				<input type="submit" value="Search" />
				<script>
					/* Language Radio */
					var langId = ${film.languageId };
					switch (langId) {
					case 1:
						var lang = document.getElementById("english");
						lang.checked = true;
						break;
					case 2:
						var lang = document.getElementById("italian");
						lang.checked = true;
						break;
					case 3:
						var lang = document.getElementById("japanese");
						lang.checked = true;
						break;
					case 4:
						var lang = document.getElementById("mandarin");
						lang.checked = true;
						break;
					case 5:
						var lang = document.getElementById("french");
						lang.checked = true;
						break;
					case 6:
						var lang = document.getElementById("german");
						lang.checked = true;
						break;
					}
					
					/* Rental Duration */
					//set from film
					var rentDur = document.getElementById("rentalDuration");
					rentDur.value = ${film.rentalDuration };
					//slider
					var slider = document.getElementById("rentalDuration");
					var output = document.getElementById("rentalDays");
					output.innerHTML = slider.value;
					slider.oninput = function() {
						output.innerHTML = this.value;
					}
					
					/* Rental Duration Slider */
					var slider = document.getElementById("rentalDuration");
					var output = document.getElementById("rentalDays");
					output.innerHTML = slider.value;
					slider.oninput = function() {
						output.innerHTML = this.value;
					}
					
					/* Rating Radio */
					var rat = document.getElementById("${film.rating }");
					rat.checked = true;
					
					
				</script>
			</form>
		</c:otherwise>
	</c:choose>
	
	
	
</body>
</html>