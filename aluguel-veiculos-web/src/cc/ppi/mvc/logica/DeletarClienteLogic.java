package cc.ppi.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.ClienteDao;
import cc.ppi.jdbc.modelo.Cliente;

public class DeletarClienteLogic implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		boolean deletou = false;
		String cpf = req.getParameter("cpf");
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		
		// busca a conexão pendurada na requisição
		Connection conexao = (Connection) req.getAttribute("connection");
		
		// passe a conexão no construtor
		ClienteDao dao = new ClienteDao(conexao);
		List<Cliente> listaClientes = dao.getBusca(cliente.getCpf());
		for (Cliente iterador : listaClientes) {
			dao.remove(iterador);
			deletou = true;
		}
		req.setAttribute("resultado", deletou);
		return "deletarCliente.jsp";
	}
}