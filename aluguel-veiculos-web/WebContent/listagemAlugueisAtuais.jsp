<%@page import="java.util.Calendar"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="cc.ppi.jdbc.dao.ReservaDao"%>
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
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<c:import url="cabecalho.jsp" />

	<div align="center">
		<%
			int contadorReservas = 0;
		%>

		<p />
		<table border=1 cellspacing=0 cellpadding=5>
			<tr align="center">
				<td><b>Código</b></td>
				<td><b>Renavan do veículo</b></td>
				<td><b>CPF do Cliente</b></td>
				<td><b>Retirada</b></td>
				<td><b>Devolução</b></td>
				<td><b>Valor</b>
				<td><b>Opções</b></td>
			</tr>
			<%
				Connection connection = (Connection) request.getAttribute("connection");
				ReservaDao dao = new ReservaDao(connection);
				List<Reserva> reservas = dao.getLista();

				for (Reserva reserva : reservas) {
					int tempoAntes = reserva.getRdata().compareTo(Calendar.getInstance());
					int tempoDepois = reserva.getDdata().compareTo(Calendar.getInstance());
					if (tempoAntes < 0 & tempoDepois > 0) {
						String rData = "";
						if (reserva.getRdata() != null) {
							rData = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getRdata().getTime()) + ", "
									+ reserva.getRhora() + "h";
						} else {
							rData += reserva.getRhora();
						}
						String dData = "";
						if (reserva.getDdata() != null) {
							dData = new SimpleDateFormat("dd/MM/yyyy").format(reserva.getDdata().getTime()) + ", "
									+ reserva.getDhora() + "h";
						} else {
							dData += reserva.getDhora();
						}
			%>
			<tr>
				<td><%=reserva.getCodigo()%></td>
				<td><%=reserva.getRenavan()%></td>
				<td><%=reserva.getCpf()%></td>
				<td><%=rData%></td>
				<td><%=dData%></td>
				<td>R$ <%=reserva.getValor()%>0
				</td>
				<td><form action="mvc?logica=CancelaReservaLogic" method="post">
						<input type="hidden" name="codigo"
							value="<%=reserva.getCodigo()%>"> <input type="submit"
							value="Deletar" />
					</form></td>
			</tr>
			<%
				contadorReservas++;
					}
				}
			%>
		</table>
		<p />
		Quantidade de reservas encontradas:
		<%=contadorReservas%>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>