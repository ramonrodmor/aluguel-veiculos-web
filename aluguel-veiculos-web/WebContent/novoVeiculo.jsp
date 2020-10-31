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
		<form action="mvc?logica=NovoVeiculoLogic" method="post">
			<h3>Dados do Veículo</h3>
			Todos os campos são de preenchimento obrigatório.
			<p/>
			<table>
				<tr>
					<td>Renavan (11 dígitos):</td>
					<td><input type="text" name="renavan" /></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" name="nome" /></td>
				</tr>
				<tr>
					<td>Categoria:</td>
					<td><select name="categoria">
							<option value="economicos">Econômico</option>
							<option value="intermediarios">Intermediário</option>
							<option value="SUV">SUV</option>
							<option value="executivos">Executivo</option>
					</select></td>
				</tr>
				<tr>
					<td>Ano (aaaa):</td>
					<td><input type="text" name="ano" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Cadastrar" /></td>
				</tr>
			</table>

		</form>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>