package cc.ppi.mvc.logica;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.ppi.jdbc.dao.ClienteDao;
import cc.ppi.jdbc.dao.ReservaDao;
import cc.ppi.jdbc.dao.VeiculoDao;
import cc.ppi.jdbc.modelo.Cliente;
import cc.ppi.jdbc.modelo.Reserva;

public class ConfirmarReservaLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// buscando dados na requisição
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		String telefone = request.getParameter("telefone");
		String renavan = request.getParameter("renavan");
		float valor = Float.parseFloat(request.getParameter("valor"));
		String dDataEmTexto = request.getParameter("ddata");
		Calendar dData = null;
		String dHora = request.getParameter("dhora");
		String rDataEmTexto = request.getParameter("rdata");
		Calendar rData = null;
		String rHora = request.getParameter("rhora");
		if (!dataEmTexto.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
			} catch (ParseException e) {
				dataEmTexto = "";
			}
		}
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
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		cliente.setEmail(email);
		cliente.setDataNascimento(dataNascimento);
		cliente.setTelefone(telefone);

		if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty() || cliente.getEndereco().isEmpty()
				|| cliente.getTelefone().isEmpty()) {
			return "dadosImcompletos.jsp";
		} else {
			ClienteDao clienteDao = new ClienteDao((Connection) request.getAttribute("connection"));
			List<Cliente> resultado = clienteDao.getBusca(cliente.getCpf());
			if (resultado.isEmpty()) {
				clienteDao.adiciona(cliente);
			} else {
				clienteDao.altera(cliente);
			}
			Reserva reserva = new Reserva();
			reserva.setDdata(dData);
			reserva.setDhora(dHora);
			reserva.setRdata(rData);
			reserva.setRhora(rHora);
			reserva.setRenavan(renavan);
			reserva.setCpf(cpf);
			reserva.setValor(valor);
			Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
			long codigo = dataDeHoje.getTime();
			reserva.setCodigo(String.valueOf(codigo));
			
			// testa se já existe uma reserva para este cpf
			ReservaDao reservaDao = new ReservaDao((Connection) request.getAttribute("connection"));
			List<Reserva> reservasCpf= reservaDao.getBusca(reserva.getCpf());
			for (Reserva iterador : reservasCpf) {
				int novaRvelhaR = reserva.getRdata().compareTo(iterador.getRdata());
				int novaDvelhaR = reserva.getDdata().compareTo(iterador.getRdata());
				int novaRvelhaD = reserva.getRdata().compareTo(iterador.getDdata());
				int novaDvelhaD = reserva.getDdata().compareTo(iterador.getDdata());
				
				if ((novaRvelhaR<0 & novaDvelhaR<0) || (novaRvelhaD>0 && novaDvelhaD>0)) {
					reservaDao.adiciona(reserva);
					
					VeiculoDao veiculoDao = new VeiculoDao((Connection) request.getAttribute("connection"));
					veiculoDao.reservaVeiculo(reserva.getRenavan());
					
					request.setAttribute("reserva", reserva);
					return "resultadoReserva.jsp";
				} else {
					return "reservasMultiplas.jsp";
				}
			}
		}
		return "erro.jsp";
	}
}