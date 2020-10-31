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
			String nome = (String) request.getAttribute("nome");

			if (nome.isEmpty()) {
				String renavan = (String) request.getAttribute("renavan");
		%>
		O Renavan <%=renavan%> já foi cadastrado para outro veículo. Insira um renavan diferente.
		<%
			} else {
		%>
		O veículo <%=nome%> foi cadastrado/alterado com sucesso e já está pronto para ser locado.
		<%
			}
		%>

	</div>

	<c:import url="rodape.jsp" />
</body>
</html>