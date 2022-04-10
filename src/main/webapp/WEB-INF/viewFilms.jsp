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
</head>
<body>

	<h1>Films containing: ${searchTerm}</h1>

	<c:choose>

		<c:when test="${empty films}">
            No Films by search term: ${searchTerm}
         </c:when>
		<c:otherwise>
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th>Update</th>
						<th>Delete</th>
						<th>ID</th>
						<th>Title</th>
						<th>Year</th>
						<th>Description</th>
						<th>Lan</th>
						<th>Rent len</th>
						<th>Rental Rate</th>
						<th>length</th>
						<th>Cost</th>
						<th>Rating</th>
						<th>SpecialFeatures</th>
					</tr>
				</thead>
				<c:forEach items="${films}" var="film">
					<td><a href="displayFilm.do?filmid=${film.id}"><button
								class="btn btn-secondary">Update</button></a></td>
					<td><a
						href="removeFilmFromTable.do?filmid=${film.id}&searchTerm=${searchTerm}"><button
								class="btn btn-danger"
								onclick="return confirm('Are you sure you want to delete ${film.title}?')">Remove</button></a></td>
					<td>${film.id}</td>
					<td>${film.title}</td>
					<td>${film.releaseYear}</td>
					<td>${film.description}</td>
					<td>${film.languageId}</td>
					<td>${film.rentalDuration}</td>
					<td>${film.rental_rate}</td>
					<td>${film.length}</td>
					<td>${film.replacementCost}</td>
					<td>${film.rating}</td>
					<td>${film.specialFeatures}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>





</body>
</html>