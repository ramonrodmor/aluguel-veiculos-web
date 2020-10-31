package cc.ppi.mvc.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.ppi.jdbc.modelo.Reserva;

public class EscolheVeiculoLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// buscando dados na requisição
		String dDataEmTexto = request.getParameter("ddata");
		Calendar dData = null;
		String dHora = request.getParameter("dhora");
		String rDataEmTexto = request.getParameter("rdata");
		Calendar rData = null;
		String rHora = request.getParameter("rhora");
		try {
			Date ddate = new SimpleDateFormat("dd/MM/yyyy").parse(dDataEmTexto);
			Date rdate = new SimpleDateFormat("dd/MM/yyyy").parse(rDataEmTexto);
			dData = Calendar.getInstance();
			rData = Calendar.getInstance();
			dData.setTime(ddate);
			rData.setTime(rdate);
		} catch (ParseException e) {
			dDataEmTexto = "";
			rDataEmTexto = "";
		}		
		
		Reserva reserva = new Reserva();
		reserva.setDdata(dData);
		reserva.setDhora(dHora);
		reserva.setRdata(rData);
		reserva.setRhora(rHora);
		
		String renavan = request.getParameter("renavan");
		reserva.setRenavan(renavan);
		
		String categoria = (String) request.getParameter("categoria");

		// Calcula valor
		long dias = TimeUnit.MILLISECONDS
				.toDays(reserva.getDdata().getTimeInMillis() - reserva.getRdata().getTimeInMillis());
		switch (categoria) {
		case "economicos":
			reserva.setValor((float) (dias*70.0));
			break;
		case "intermediarios":
			reserva.setValor((float) (dias*90.0));
			break;
		case "SUV":
			reserva.setValor((float) (dias*120.0));
			break;
		case "executivos":
			reserva.setValor((float) (dias*150.0));
			break;
		default:
			break;
		}
		
		request.setAttribute("reserva", reserva);
		return "confirmarReserva.jsp";
	}
}