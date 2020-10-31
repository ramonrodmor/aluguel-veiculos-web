package cc.ppi.mvc.logica;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.ppi.jdbc.dao.ClienteDao;
import cc.ppi.jdbc.modelo.Cliente;

public class NovoClienteLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// buscando dados na requisição
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		String telefone = request.getParameter("telefone");
		if (!dataEmTexto.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
			} catch (ParseException e) {
				dataEmTexto = "";
			}
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
				request.setAttribute("nome", cliente.getNome());
				return "resultadoNovoCliente.jsp";
			} else {
				request.setAttribute("cpf", cliente.getCpf());
				return "resultadoNovoCliente.jsp";
			}
		}
	}
}