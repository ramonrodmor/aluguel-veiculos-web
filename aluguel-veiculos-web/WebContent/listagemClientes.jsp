<%@page import="cc.ppi.jdbc.modelo.Reserva"%>
<%@page import="cc.ppi.jdbc.dao.ReservaDao"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="cc.ppi.jdbc.dao.ClienteDao"%>
<%@page import="cc.ppi.jdbc.modelo.Cliente"%>
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
			int contadorClientes = 0;
		%>

		<a href="novoCliente.jsp">Cadastrar Novo Cliente</a>
		<p />
		<table border=1 cellspacing=0 cellpadding=5>
			<tr align="center">
				<td><b>Nome</b></td>
				<td><b>CPF</b></td>
				<td><b>E-mail</b></td>
				<td><b>Endereço</b></td>
				<td><b>Nascimento</b></td>
				<td><b>Telefone</b></td>
				<td><b>Reservas</b></td>
				<td colspan="2"><b>Opções</b></td>
			</tr>
			<%
				Connection connection = (Connection) request.getAttribute("connection");
				ClienteDao clienteDao = new ClienteDao(connection);
				List<Cliente> clientes = clienteDao.getLista();
				ReservaDao reservaDao = new ReservaDao(connection);

				for (Cliente cliente : clientes) {
					String data = "";
					if (cliente.getDataNascimento() != null) {
						data = new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento().getTime());
					}
			%>
			<tr>
				<td><%=cliente.getNome()%></td>
				<td><%=cliente.getCpf()%></td>
				<td><%=cliente.getEmail()%></td>
				<td><%=cliente.getEndereco()%></td>
				<td><%=data%></td>
				<td><%=cliente.getTelefone()%></td>
				<%
					List<Reserva> reservas = reservaDao.getBusca(cliente.getCpf());
						for (Reserva reserva : reservas) {
				%>

				<td>
					<p>
						Código da Reserva:
						<%=reserva.getCodigo()%> <br>
						Valor da Reserva:
						<%=reserva.getValor()%></p>
				</td>
				<%
					}
				%>
				<td><form action="mvc?logica=BuscaClienteLogic" method="post">
						<input type="hidden" name="cpf" value="<%=cliente.getCpf()%>">
						<input type="submit" value="Alterar" />
					</form></td>
				<td><form action="mvc?logica=DeletarClienteLogic" method="post">
						<input type="hidden" name="cpf" value="<%=cliente.getCpf()%>">
						<input type="submit" value="Deletar" />
					</form></td>
			</tr>
			<%
				contadorClientes++;
				}
			%>
		</table>
		<p />
		Quantidade de clientes encontrados:
		<%=contadorClientes%>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>