<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br" dir="ltr">
<head>
	<meta charset="UTF-8" />
	<title>Series que vc vê</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
</head>
</head>
<body>
	<div class="container mt-4">
		<header class="jumbotron">
			<h1>Séries que você <i class="far fa-eye"></i> - Adicione sua Série</h1>
		</header>
		<form method="post" action="serie">
			<div class="form-group">
				<label for="nome">Nome da série</label>
				<input type="text" id="nome" name="nome" class="form-control" placeholder="Informe o nome da série que você vê" />
			</div>
			<div class="d-flex justify-content-end">
				<button type="submit" class="btn btn-primary mr-2"><i class="far fa-hdd mr-2"></i>Salvar</button>
				<a href="index.jsp" class="btn btn-secondary"><i class="fas fa-arrow-alt-circle-left mr-2"></i>Cancelar</a>
			</div>
		</form>
	</div>
</body>
</html>