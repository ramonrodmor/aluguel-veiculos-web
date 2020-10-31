<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="cc.ppi.jdbc.dao.VeiculoDao"%>
<%@page import="cc.ppi.jdbc.modelo.Veiculo"%>
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
			int contadorVeiculos = 0;
		%>

		<a href="novoVeiculo.jsp">Cadastrar Novo Veículo</a>
		<p />
		<table border=1 cellspacing=0 cellpadding=5>
			<tr align="center">
				<td><b>Nome</b></td>
				<td><b>Renavan</b></td>
				<td><b>Categoria</b></td>
				<td><b>Ano</b></td>
				<td><b>Estado</b></td>
				<td colspan="2"><b>Opções</b></td>
			</tr>
			<%
				Connection connection = (Connection) request.getAttribute("connection");

				VeiculoDao dao = new VeiculoDao(connection);
				List<Veiculo> veiculos = dao.getLista();

				for (Veiculo veiculo : veiculos) {
					String locado = "";
					if (veiculo.isLocado()) {
						locado = "Locado";
					} else
						locado = "Disponível";
			%>
			<tr>
				<td><%=veiculo.getNome()%></td>
				<td><%=veiculo.getRenavan()%></td>
				<td><%=veiculo.getCategoria()%></td>
				<td><%=veiculo.getAno()%></td>
				<td><%=locado%></td>
				<td><form action="mvc?logica=BuscaVeiculoLogic" method="post">
						<input type="hidden" name=renavan
							value="<%=veiculo.getRenavan()%>"> <input type="submit"
							value="Editar" />
					</form></td>
				<td><form action="mvc?logica=DeletarVeiculoLogic" method="post">
						<input type="hidden" name="renavan"
							value="<%=veiculo.getRenavan()%>"> <input type="submit"
							value="Deletar" />
					</form></td>
			</tr>
			<%
				contadorVeiculos++;
				}
			%>
		</table>
		<p />
		Quantidade de veículos encontrados:
		<%=contadorVeiculos%>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>