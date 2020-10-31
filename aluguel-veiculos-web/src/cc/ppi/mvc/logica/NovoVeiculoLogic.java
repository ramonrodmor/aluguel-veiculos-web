package cc.ppi.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cc.ppi.jdbc.dao.VeiculoDao;
import cc.ppi.jdbc.modelo.Veiculo;

public class NovoVeiculoLogic implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// buscando dados na requisição
		String renavan = (String) request.getParameter("renavan");
		String nome = (String) request.getParameter("nome");
		String categoria = (String) request.getParameter("categoria");
		String ano = (String) request.getParameter("ano");
		
		if (renavan.isEmpty() || nome.isEmpty() || categoria.isEmpty() || ano.isEmpty()) {
			return "dadosIncompletos.jsp";
		} else {
			Veiculo novo = new Veiculo();
			novo.setRenavan(renavan);
			novo.setNome(nome);
			novo.setCategoria(categoria);
			novo.setAno(ano);
			novo.setLocado(false);
			
			// verificar se o veículo já foi cadastrado antes
			Connection conexao = (Connection) request.getAttribute("connection");
			VeiculoDao dao = new VeiculoDao(conexao);
			List<Veiculo> veiculos = dao.getBusca(novo.getRenavan());
			
			if (veiculos.isEmpty()) {
				dao.adiciona(novo);
				request.setAttribute("nome", novo.getNome());
				return "resultadoNovoVeiculo.jsp";
			} else {
				request.setAttribute("renavan", novo.getRenavan());
				return "resultadoNovoVeiculo.jsp";
			}
		}
	}
}