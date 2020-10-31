<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="cc.ppi.jdbc.modelo.Reserva"%>
<%@page import="cc.ppi.jdbc.modelo.Veiculo"%>
<%@page import="java.util.List"%>
<%@page import="cc.ppi.jdbc.dao.VeiculoDao"%>
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
		<h3>Dados do Veiculo</h3>
		<p />
		<table border=1 cellspacing=0 cellpadding=5>
			<tr align="center">
				<td><b>Nome</b></td>
				<td><b>Renavan</b></td>
				<td><b>Categoria</b></td>
				<td><b>Ano</b></td>
				<td colspan="2"><b>Opções</b></td>
			</tr>
			<%				
			Reserva reserva = (Reserva) request.getAttribute("reserva");
			
			String ddata = "", rdata = "";
			ddata = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getDdata().getTime());
			rdata = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getRdata().getTime());
			
			Connection connection = (Connection) request.getAttribute("connection");
			VeiculoDao dao = new VeiculoDao(connection);
			List<Veiculo> veiculos = dao.getDisponivel();
				String valor = null;

				for (Veiculo veiculo : veiculos) {
					switch (veiculo.getCategoria()) {
					case "economicos":
						valor = "R$ 70,00 / dia";
						break;
					case "intermediarios":
						valor = "R$ 90,00 / dia";
						break;
					case "SUV":
						valor = "R$ 120,00 / dia";
						break;
					case "executivos":
						valor = "R$ 150,00 / dia";
						break;
					}
			%>
			<tr>
				<td><%=veiculo.getNome()%></td>
				<td><%=veiculo.getRenavan()%></td>
				<td><%=veiculo.getCategoria()%></td>
				<td><%=veiculo.getAno()%></td>
				<td><%=valor%></td>
				<td><form action="mvc?logica=EscolheVeiculoLogic" method="post">
						<input type="hidden" name="renavan" value="<%=veiculo.getRenavan()%>">
						<input type="hidden" name="categoria" value="<%=veiculo.getCategoria()%>">
						<input type="hidden" name="ddata" value="<%=ddata%>">
						<input type="hidden" name="dhora" value="<%=reserva.getDhora()%>">
						<input type="hidden" name="rdata" value="<%=rdata%>">
						<input type="hidden" name="rhora" value="<%=reserva.getRhora()%>">
						<input type="submit" value="Escolher" />
					</form></td>
			</tr>
			<%
				}
			%>
		</table>
		<p />
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>