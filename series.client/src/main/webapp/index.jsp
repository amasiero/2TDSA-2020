<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br" dir="ltr">
<head>
	<meta charset="UTF-8" />
	<title>Series que vc vê</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
</head>
<body>
	<div class="container mt-4">
		<header class="jumbotron">
			<h1>Séries que você <i class="far fa-eye"></i></h1>
		</header>
		<h4>Séries cadastradas</h4>
		<jsp:useBean id="service" scope="page" class="br.com.fiap.series.client.service.SerieService" />
		<ul class="list-group">
			<c:forEach var="serie" items="${service.series}">
				<li class="list-group-item d-flex justify-content-between">
					${serie.nome}
					<a href="serie?id=${serie.id}" class="btn btn-danger">
						<i class="fas fa-trash-alt mr-2"></i>
						Excluir
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="d-flex justify-content-end">
			<a href="adiciona.jsp" class="btn btn-primary mt-3"><i class="fas fa-plus-circle mr-2"></i>Adicionar</a>
		</div>
	</div>
</body>
</html>
