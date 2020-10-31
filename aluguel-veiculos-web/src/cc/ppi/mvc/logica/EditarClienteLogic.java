package cc.ppi.mvc.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.ClienteDao;
import cc.ppi.jdbc.modelo.Cliente;

public class EditarClienteLogic implements Logica {

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
			// verificar se o veículo já foi cadastrado antes
			Connection conexao = (Connection) request.getAttribute("connection");
			ClienteDao dao = new ClienteDao(conexao);
			dao.altera(cliente);

			request.setAttribute("nome", cliente.getNome());
			return "resultadoNovoCliente.jsp";
		}
	}
}