package cc.ppi.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cc.ppi.jdbc.modelo.Reserva;

public class NovaReservaLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// buscando dados na requisição
		String rhora = request.getParameter("rhora");
		String dhora = request.getParameter("dhora");
		String dataREmTexto = "";
		dataREmTexto = request.getParameter("rdata");
		Calendar rdata = null;
		String dataDEmTexto = "";
		dataDEmTexto = request.getParameter("ddata");
		Calendar ddata = null;
		if (!dataREmTexto.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataREmTexto);
				rdata = Calendar.getInstance();
				rdata.setTime(date);
			} catch (ParseException e) {
				dataREmTexto = "";
			}
		}
		if (!dataDEmTexto.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataDEmTexto);
				ddata = Calendar.getInstance();
				ddata.setTime(date);
			} catch (ParseException e) {
				dataDEmTexto = "";
			}
		}
		
		if (dataREmTexto.isEmpty() || rhora.isEmpty() || dataDEmTexto.isEmpty() || dhora.isEmpty()) {
			return "dadosIncompletos.jsp";
		} else {
			Reserva reserva = new Reserva();
			reserva.setRdata(rdata);
			reserva.setRhora(rhora);
			reserva.setDdata(ddata);
			reserva.setDhora(dhora);
			request.setAttribute("reserva", reserva);
			return "escolheVeiculo.jsp";
		}
	}
}