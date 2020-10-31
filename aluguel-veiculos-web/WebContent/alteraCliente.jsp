<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cc.ppi.jdbc.modelo.Cliente"%>
<%@page import="cc.ppi.jdbc.modelo.Veiculo"%>
<%@page import="com.mysql.jdbc.Connection"%>
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

	<%
		Cliente resultado = (Cliente) request.getAttribute("cliente");
		String data = "";
		if (resultado.getDataNascimento() != null) {
			data = new SimpleDateFormat("dd/MM/yyyy").format(resultado.getDataNascimento().getTime());
		}
	%>

	<div align="center">
		<form action="mvc?logica=EditarClienteLogic" method="post">
			<h3>Dados do Cliente</h3>
			<h4>Os campos em negrito são de preenchimento obrigatório.</h4>
			<p />
			<table>
				<tr>
					<td><b>Nome: </b></td>
					<td><input type="text" name="nome"
						value="<%=resultado.getNome()%>" /></td>
				</tr>
				<tr>
					<td><b>CPF:</b></td>
					<td><%=resultado.getCpf()%></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><input type="text" name="email"
						value="<%=resultado.getEmail()%>" /><br /></td>
				</tr>

				<tr>
					<td><b>Endereço: </b></td>
					<td><input type="text" name="endereco"
						value="<%=resultado.getEndereco()%>" /><br /></td>
				</tr>

				<tr>
					<td>Data de Nascimento: (dd/mm/aaaa)</td>
					<td><input type="text" name="dataNascimento" value="<%=data%>"/><br /></td>
				</tr>

				<tr>
					<td><b>Telefone: </b></td>
					<td><input type="text" name="telefone"
						value="<%=resultado.getTelefone()%>" /><br /></td>
				</tr>

				<tr>
					<td><input type="hidden" name="cpf"
						value="<%=resultado.getCpf()%>"></td>
					<td><input type="submit" value="Gravar" /></td>
				</tr>
			</table>

		</form>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>