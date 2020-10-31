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
		<form action="mvc?logica=NovaReservaLogic" method="post">
			<h3>Dados da Reserva</h3>
			Todos os campos são de preenchimento obrigatório.
			<p/>
			<table>
				<tr>
					<td>Data de Retirada (dd/mm/aaaa):</td>
					<td><input type="text" name="rdata" /></td>
				</tr>
				<tr>
					<td>Horário de Retirada (hh):</td>
					<td><input type="text" name="rhora" /></td>
				</tr>
				<tr>
					<td>Data de Devolução (dd/mm/aaaa):</td>
					<td><input type="text" name="ddata" /></td>
				</tr>
				<tr>
					<td>Horário de Devolução (hh):</td>
					<td><input type="text" name="dhora" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Continuar" /></td>
				</tr>
			</table>

		</form>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>