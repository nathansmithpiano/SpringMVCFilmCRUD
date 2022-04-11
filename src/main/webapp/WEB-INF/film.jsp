<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js">
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet" href="styles/tableStyles.css">
<link rel="stylesheet" href="styles/navbar.css">
</head>
<body>

	<nav class="navbar navbar-expand-md style="background-color:#e3f2fd;">
		<a class="navbar-brand" href="#">FilmMVC</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="searchFilm.do?filmid=">Search Films</a></li>
				<li><a class="nav-link" href="goAddFilm.do">Add Film</a></li>
			</ul>
		</div>
	</nav>
	
	<div id="filmForm">
		<c:choose>
			<c:when test='${empty film }'>
				<h4>FILM NOT FOUND</h4>
			</c:when>
			<c:otherwise>
				<!-- new stuff -->
				<h3>Film Details</h3>
				
				<form id="filmForm" action="addFilm.do" method="GET">
					<!-- Title -->
					<input type="hidden" id="id" name="id" value="${film.id }">
					<label for="title">Title:</label>
					<input type="text" id="title" name="title" size="30" value="${film.title}" required>
					<!-- Description -->
					<br>
					<label for="description">Description:</label>
					<input type="text" name="description" size="30" value="${film.description}">
					<!-- Release Year -->
					<br>
					<label for="releaseYear">Release Year:</label>
					<input type="number" id="releaseYear" name="releaseYear" min="1895" max="2022" value="${film.releaseYear}">
	
					<!-- Language -->
					<br>
					<p>Language:</p>
					<input type="radio" id="english" name="languageId" value="1">
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
					<input type="range" id="rentalDuration" name="rentalDuration" min="1" max="7" value="${film.rentalDuration}" required>
					Days: <span id="rentalDays"></span>
					
					<!-- Rental Rate -->
					<br>
					<label for="rental_rate">Rental Rate</label>
					<input type="text" name="rental_rate" step="0.01" size="6" min="0" value="${film.rental_rate}">
					<!-- Length -->
					<br>
					<label for="length">Length (min):</label>
					<input type="number" id="length" name="length" min="0" value="${film.length}">
					<!-- Replacement Cost -->
					<br>
					<label for="replacementCost">Replacement Cost</label>
					<input type="text" name="replacementCost" step="0.01" size="6" value="${film.replacementCost}">
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
	
			       <button type="submit" formaction="updateFilm.do" onclick="return confirm('UPDATE: Are you sure?')">
			       	Update
			       	</button>
			       <button type="submit" formaction="removeFilm.do?filmid=${film.id}" onclick="return confirm('REMOVE: Are you sure?')">
			       	Remove
			       </button>
				</form>
				<script>
							
					/* Release Year remove if 0 */
					var relYear = ${film.releaseYear}; 
					if (relYear == 0) {
						var field = document.getElementById("releaseYear");
						field.setAttribute('value', "");
					}
					var len = ${film.length}; 
					if (len == 0) {
						var field = document.getElementById("length");
						field.setAttribute('value', "");
					}
				
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
					
					/* Rating Radio */
					var rat = document.getElementById("${film.rating }");
					rat.checked = true;
					
					/* Rental Duration Slider */
					var slider = document.getElementById("rentalDuration");
					var output = document.getElementById("rentalDays");
					output.innerHTML = slider.value;
					slider.oninput = function() {
						output.innerHTML = this.value;
					}
				</script>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>