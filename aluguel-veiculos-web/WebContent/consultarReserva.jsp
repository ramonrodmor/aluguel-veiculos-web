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
		<form action="mvc?logica=BuscaReservaLogic" method="post">
			Código da reserva: <input type="text" name="chave" /> <input
				type="submit" value="Consultar" />
		</form>
	</div>

	<c:import url="rodape.jsp" />
</body>
</html>