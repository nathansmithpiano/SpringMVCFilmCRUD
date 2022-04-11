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
				<li class="nav-item"><a class="nav-link"
					href="searchFilm.do?filmid=">Search Films</a></li>
				<li><a class="nav-link" href="goAddFilm.do">Add Film</a></li>
			</ul>
		</div>
	</nav>


	<h1>Film Search</h1>

	<form action="searchFilm.do" id="searchForm">
		<div class="searchByKeywordFrom">
			<label id="keywordLabel">Keyword</label> <input type="text"
				name="filmid">
			<button type="submit" class="btn btn-success">Search</button>
		</div>
	</form>

	<form action="showFilm.do" id="searchForm">
		<div class="searchByKeywordFrom">
			<label id="keywordLabel">Film ID</label> <input type="number" min="0"
				name="filmid" placeholder="${searchTerm}">
			<button type="submit" class="btn btn-success">Search</button>
		</div>
	</form>


	<!-- --- -->
	<c:choose>
		<c:when test="${param.filmid == -1 }">
			<c:if test="${param.removed == true }">
				<div class="filmViewContainer">
					<h5>Film successfully removed.</h5>
				</div>
			</c:if>
			<c:if test="${param.removed == false }">
				<div class="filmViewContainer">
					<h5>ERROR: Film was NOT removed.</h5>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${empty film }">
					<div class="filmViewContainer">
						<div class="item">
							<h4>0 films found</h4>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:if test="${param.updated == false }">
						<div class="item"><h4>ERROR: Film was NOT updated.</h4></div>
					</c:if>
					<c:if test="${param.updated == true }">
						<div class="item"><h4>Film successfully updated.</h4></div>
					</c:if>
					<c:if test="${param.added == false }">
						<div class="item"><h4>ERROR: Film was NOT added.</h4></div>
					</c:if>
					<c:if test="${param.added == true }">
						<div class="item"><h4>Film successfully added.</h4></div>
					</c:if>
					<div class="filmViewContainer">
						<div class="item">
							<h5>Title: ${film.title}</h5>
						</div>
						<br>
						<div class="item">Film ID: ${film.id}</div>
						<c:choose>
							<c:when test="${film.releaseYear != 0}">
								<div class="item">Release Year: ${film.releaseYear}</div>
							</c:when>
							<c:otherwise>
								<div class="item">Release Year: </div>
							</c:otherwise>
						</c:choose>
						<div class="item">Description: ${film.description}</div>
						<div class="item">Language ID: ${film.languageId}</div>
						<div class="item">Rental Duration: ${film.rentalDuration}</div>
						<div class="item">Rental Rate: ${film.rental_rate}</div>
						<c:choose>
							<c:when test="${film.length != 0}">
								<div class="item">Length: ${film.length}</div>
							</c:when>
							<c:otherwise>
								<div class="item">Length: </div>
							</c:otherwise>
						</c:choose>
						<div class="item">Replacement Cost: ${film.replacementCost}</div>
						<div class="item">Rating: ${film.rating}</div>
						<div class="item">Special Features: ${film.specialFeatures}</div>
						<div class="item">Category: ${film.category}</div>
		
						<h4>Cast</h4>
						<c:forEach items="${actors}" var="actor">
							<h4>
								<c:out value="${actor.firstName} ${actor.lastName}" />
							</h4>
						</c:forEach>
					</div>
					<c:if test="${film.id > 1000}">
						<div class="filmViewContainerButtons">
							<a href="displayFilm.do?filmid=${film.id}">
								<button class="btn btn-secondary">Update</button>
							</a> <a
								href="removeFilm.do?filmid=${film.id}">
								
								<button class="btn btn-danger"
									onclick="return confirm('Are you sure you want to delete ${film.title}?')">Remove</button>
							</a>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</body>
</html>