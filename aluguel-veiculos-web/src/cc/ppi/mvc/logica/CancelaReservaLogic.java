package cc.ppi.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.ReservaDao;
import cc.ppi.jdbc.dao.VeiculoDao;
import cc.ppi.jdbc.modelo.Reserva;

public class CancelaReservaLogic implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String codigo = req.getParameter("codigo");
		Reserva reserva = new Reserva();
		reserva.setCodigo(codigo);
		
		// busca a conexão pendurada na requisição
		Connection connection = (Connection) req.getAttribute("connection");
		
		VeiculoDao veiculoDao = new VeiculoDao(connection);
		veiculoDao.devolveVeiculo(reserva.getRenavan());
		ReservaDao reservaDao = new ReservaDao(connection);
		reservaDao.remove(reserva);
		System.out.println("Cancelando reserva...");
		req.setAttribute("codigo", codigo);
		return "reservaCancelada.jsp";
	}
}