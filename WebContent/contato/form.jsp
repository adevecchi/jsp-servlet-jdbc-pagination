<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin>
	<title>Gerenciameto de Contatos</title>
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
		      		<a class="nav-link" href="<%= request.getContextPath() %>">Início <span class="sr-only">(current)</span></a>
		      		<a class="nav-link active" href="<%= request.getContextPath() %>/contatos">Contatos</a>
		    	</div>
		  	</div>
		</nav>
	</header>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-5 mt-5">
	        <div class="card">
	        	<div class="card-header">
					<c:if test="${contato != null}">
                    	<h4>Atualizar Contato</h4>
                    </c:if>
                    <c:if test="${contato == null}">
                    	<h4>Adicionar Novo Contato</h4>
                    </c:if>
				</div>
	            <div class="card-body">
	            	<c:if test="${contato != null}">
	            	<form id="frm-salvar" action="contatos" method="post">
	            		<input type="hidden" name="cmd" value="put">
	            		<input type="hidden" name="id" value="<c:out value='${contato.id}' />">
	            	</c:if>
	            	<c:if test="${contato == null}">
	            	<form id="frm-salvar" action="contatos" method="post">
	            		<input type="hidden" name="cmd" value="post">
	            	</c:if>
		                <div class="form-group">
		                    <label for="nome">Nome</label>
		                    <input type="text" value="<c:out value='${contato.nome}' />" class="form-control" id="nome" name="nome" required>
		                </div>
		
		                <div class="form-group">
		                    <label for="email">Email</label>
		                    <input type="email" value="<c:out value='${contato.email}' />" class="form-control" id="email" name="email" required>
		                </div>
		
		                <div class="form-group">
		                    <label for="telefone">Telefone</label>
		                    <input type="text" value="<c:out value='${contato.telefone}' />" class="form-control" id="telefone" name="telefone" required maxlength="15">
		                </div>
		
		                <button type="submit" class="btn btn-success btn-salvar" style="width:6rem;">Salvar</button>
		                <a href="<%= request.getHeader("referer") %>" class="btn btn-secondary btn-cancelar" style="width:6rem;">Cancelar</a>
	                </form>
	            </div>
	        </div>
	        </div>
		</div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" crossorigin></script>
    <script>
    $('#frm-salvar').on('submit', function (evt) {
    	$('.btn-salvar').prop('disabled', true);
    	$('.btn-cancelar').addClass('disabled');
    });
    </script>
</body>
</html>