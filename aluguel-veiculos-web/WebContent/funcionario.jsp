<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aluguel Ramon</title>
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:import url="cabecalho.jsp" />

	<div align="center">

		<h2>Bem vindo aos recursos para funcionários</h2>

		<table>
			<tr>
				<td><a href="listagemClientes.jsp">Manutenção de dados de clientes</a></td>
			</tr>
			<tr>
				<td><a href="listagemVeiculos.jsp">Manutenção de dados de veículos</a></td>
			</tr>
			<tr>
				<td><a href="veiculosDisponiveis.jsp">Listagem de veículos diponíveis</a></td>
			</tr>
			<tr>
				<td><a href="listagemAlugueis.jsp">Listagem de alugueis realizados</a></td>
			</tr>
			<tr>
				<td><a href="listagemAlugueisAtuais.jsp">Listagem de alugueis em andamento</a></td>
			</tr>
		</table>
	</div>
	<c:import url="rodape.jsp" />
</body>
</html>