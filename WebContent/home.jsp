<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin>
	<title>Gerenciamento de Contatos</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<span class="navbar-brand text-info">adevecchi</span>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    	<div class="navbar-nav">
		      		<a class="nav-link active" href="<%= request.getContextPath() %>">Início <span class="sr-only">(current)</span></a>
		      		<a class="nav-link" href="<%= request.getContextPath() %>/contatos">Contatos</a>
		    	</div>
		  	</div>
		</nav>
	</header>
	<div class="container">
		<div class="jumbotron mt-5">
			<h1 class="display-4">Gerenciamento de Contatos</h1>
			<p class="lead">JSP, Servlet, JDBC, MySQL com Paginação.</p>
			<hr class="my-4">
			<p>Aplicativo web de gerenciamento de contatos simples com recursos para Criar, Visualizar, Atualizar e Remover em uma coleção de contatos com paginação.</p>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" crossorigin></script>
</body>
</html>