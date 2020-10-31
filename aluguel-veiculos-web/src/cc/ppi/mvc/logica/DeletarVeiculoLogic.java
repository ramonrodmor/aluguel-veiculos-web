package cc.ppi.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.VeiculoDao;
import cc.ppi.jdbc.modelo.Veiculo;

public class DeletarVeiculoLogic implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		boolean deletou = false;
		String renavan = req.getParameter("renavan");
		Veiculo veiculo = new Veiculo();
		veiculo.setRenavan(renavan);
		
		// busca a conexão pendurada na requisição
		Connection conexao = (Connection) req.getAttribute("connection");
		
		// passe a conexão no construtor
		VeiculoDao dao = new VeiculoDao(conexao);
		List<Veiculo> listaVeiculos = dao.getBusca(veiculo.getRenavan());
		for (Veiculo iterador : listaVeiculos) {
			if (!iterador.isLocado()) {
				dao.remove(iterador);
				deletou = true;
			}
		}
		req.setAttribute("resultado", deletou);
		return "deletarVeiculo.jsp";
	}
}