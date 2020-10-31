package cc.ppi.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.ClienteDao;
import cc.ppi.jdbc.modelo.Cliente;

public class BuscaClienteLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// buscando dados na requisição
		String cpf = (String) request.getParameter("cpf");

		// verificar se o veículo já foi cadastrado antes
		Connection conexao = (Connection) request.getAttribute("connection");
		ClienteDao dao = new ClienteDao(conexao);
		List<Cliente> clientes = dao.getBusca(cpf);
		Cliente resultado = clientes.get(0);

		request.setAttribute("cliente", resultado);
		return "alteraCliente.jsp";
	}
}