package cc.ppi.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.VeiculoDao;
import cc.ppi.jdbc.modelo.Veiculo;

public class BuscaVeiculoLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// buscando dados na requisição
		String renavan = (String) request.getParameter("renavan");

		// verificar se o veículo já foi cadastrado antes
		Connection conexao = (Connection) request.getAttribute("connection");
		VeiculoDao dao = new VeiculoDao(conexao);
		List<Veiculo> veiculos = dao.getBusca(renavan);
		Veiculo resultado = veiculos.get(0);

		request.setAttribute("veiculo", resultado);
		return "alteraVeiculo.jsp";
	}
}