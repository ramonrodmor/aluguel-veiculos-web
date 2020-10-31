package cc.ppi.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cc.ppi.jdbc.dao.ReservaDao;
import cc.ppi.jdbc.modelo.Reserva;

public class BuscaReservaLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String chave = request.getParameter("chave");
		
		// passe a conexão no construtor
		ReservaDao dao = new ReservaDao((Connection) request.getAttribute("connection"));
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas = dao.getBuscaCodigo(chave);
		request.setAttribute("reservas", reservas);
		return "resultadoBuscaReserva.jsp";
	}
}