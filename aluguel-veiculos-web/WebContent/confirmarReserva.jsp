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

		<%
			Reserva reserva = new Reserva();
			reserva = (Reserva) request.getAttribute("reserva");
			String rData = "";
			rData = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getRdata().getTime()) + ", " + reserva.getRhora() + "h";
			String dData = "";
			dData = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getDdata().getTime()) + ", " + reserva.getDhora() + "h";
			String ddata = "", rdata = "";
			ddata = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getDdata().getTime());
			rdata = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getRdata().getTime());
		%>

		<h2>Dados da Reserva</h2>
		<table>
			<tr>
				<td>Renavan do Veículo:</td>
				<td><%=reserva.getRenavan()%></td>
			</tr>
			<tr>
				<td>Data e Horário de Retirada:</td>
				<td><%=rData%></td>
			</tr>
			<tr>
				<td>Data e Horário de Devolução:</td>
				<td><%=dData%></td>
			</tr>
			<tr>
				<td>Valor do Aluguel:</td>
				<td>R$ <%=reserva.getValor()%>0</td>
			</tr>
			<tr>
				<td>Multa por atraso:</td>
				<td>R$ <%=reserva.getValor()*0.3%>0</td>
		</table>
		A tarifa de multa corresponde a 30% do valor total do aluguel por cada dia de atraso.
		
		<h3>Para confirmar sua reserva informe seus dados pessoais</h3>
		<h4>Os campos em negrito são obrigatórios</h4>
		<form action="mvc?logica=ConfirmarReservaLogic" method="post">
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
					<td><input type="hidden" name="ddata" value="<%=ddata%>">
						<input type="hidden" name="dhora" value="<%=reserva.getDhora()%>">
						<input type="hidden" name="rdata" value="<%=rdata%>">
						<input type="hidden" name="rhora" value="<%=reserva.getRhora()%>">
						<input type="hidden" name="renavan" value="<%=reserva.getRenavan()%>">
						<input type="hidden" name="valor" value="<%=reserva.getValor()%>"></td>
					<td><input type="submit" value="Confirmar Reserva" /></td>
				</tr>
			</table>
		</form>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>