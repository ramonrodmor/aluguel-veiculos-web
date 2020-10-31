<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cc.ppi.jdbc.modelo.Reserva"%>
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
		<h3>Dados do Cliente</h3>
		<h4>Os campos em negrito são de preenchimento obrigatório.</h4>
		<form action="mvc?logica=NovoClienteLogic" method="post">
			<table>
				<tr>
					<td><b>Nome: </b></td>
					<td><input type="text" name="nome" /><br /></td>
				</tr>

				<tr>
					<td><b>CPF:</b></td>
					<td><input type="text" name="cpf" /><br /></td>
				</tr>

				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email" /><br /></td>
				</tr>

				<tr>
					<td><b>Endereço: </b></td>
					<td><input type="text" name="endereco" /><br /></td>
				</tr>

				<tr>
					<td>Data de Nascimento: (dd/mm/aaaa)</td>
					<td><input type="text" name="dataNascimento" /><br /></td>
				</tr>

				<tr>
					<td><b>Telefone: </b></td>
					<td><input type="text" name="telefone" /><br /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Gravar" /></td>
				</tr>
			</table>
		</form>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>