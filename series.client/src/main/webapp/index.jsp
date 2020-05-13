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
					<span id="nome-serie-${serie.id}">${serie.nome}</span>
					<div id="input-serie-${serie.id}" class="input-group w-50" hidden>
						<input type="text" class="form-control" value="${serie.nome}" />
						<div class="input-group-append">
							<button class="btn btn-success" onclick="editarSerie(${serie.id})">
								<i class="fas fa-check-square"></i>
							</button>
						</div>
					</div>
					<span class="d-flex">
						<a href="#" onclick="toggleInput(${serie.id})" class="btn btn-info mr-2">
							<i class="fas fa-edit"></i>
						</a>
						<a href="serie?id=${serie.id}" class="btn btn-danger">
							<i class="fas fa-trash-alt"></i>
						</a>
					</span>
				</li>
			</c:forEach>
		</ul>
		<div class="d-flex justify-content-end">
			<a href="adiciona.jsp" class="btn btn-primary mt-3"><i class="fas fa-plus-circle mr-2"></i>Adicionar</a>
		</div>
	</div>
	<script>
		function toggleInput(serieId) {
			
			const nomeSerieEl = document.getElementById(`nome-serie-\${serieId}`);
			const inputSerieEl = document.getElementById(`input-serie-\${serieId}`);
			
			if(nomeSerieEl.hasAttribute('hidden')) {
				nomeSerieEl.removeAttribute('hidden');
				inputSerieEl.hidden = true;
			} else {
				inputSerieEl.removeAttribute('hidden');
				nomeSerieEl.hidden = true;
			}
		}
		
		function editarSerie(serieId) {
			let formData = new FormData();
			
			const nome = document.querySelector(`#input-serie-\${serieId} > input`).value;
			
			formData.append('id', serieId);
			formData.append('nome', nome);
			
			fetch('atualiza', {
				method: 'POST',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},
				body: formData
			})
			.then(_ => {
				toggleInput(serieId);
				document.getElementById(`nome-serie-\${serieId}`).textContent = nome;
			})
			.catch(e => console.log(e));
		}
	</script>
</body>
</html>
