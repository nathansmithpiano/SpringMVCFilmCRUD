<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Films</title>
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
				<li class="nav-item active"><a class="nav-link" href="home.do">Home
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="searchFilm.do?filmid=">Search Films</a></li>
			</ul>
		</div>
	</nav>


	<h1>Film Search</h1>

	<form action="searchFilm.do" id="searchForm">
		<div class="searchByKeywordFrom">
			<label id="keywordLabel">Keyword</label> <input type="text"
				name="filmid" placeholder="${searchTerm}">
			<button type="submit" class="btn btn-success">Search</button>
		</div>
	</form>

	<form action="showFilm.do" id="searchForm">
		<div class="searchByKeywordFrom">
			<label id="keywordLabel">Film ID</label> <input type="text"
				name="filmid">
			<button type="submit" class="btn btn-success">Search</button>
		</div>
	</form>


	<!-- --- -->
	<div class="filmViewContainer">
		<div class="item">${film.title}</div>
		<br>
		<div class="item">${film.releaseYear}</div>
		<div class="item">${film.description}</div>
		<div class="item">Film id: ${film.id}</div>
		<div class="item">${film.languageId}</div>
		<div class="item">${film.rentalDuration}</div>
		<div class="item">${film.rental_rate}</div>
		<div class="item">${film.length}</div>
		<div class="item">${film.replacementCost}</div>
		<div class="item">${film.rating}</div>
		<div class="item">${film.specialFeatures}</div>
		<div class="item">${film.category}</div>
		

		<h4>Cast</h4>
		<c:forEach items="${actors}" var="actor">
				<h4><c:out value="${actor.firstName} ${actor.lastName}" /></h4>

		</c:forEach>



	</div>



</body>
</html>