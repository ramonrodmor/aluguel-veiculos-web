package cc.ppi.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cc.ppi.jdbc.modelo.Veiculo;

public class VeiculoDao {

	// a conexão com o banco de dados
	private Connection connection;

	public VeiculoDao(Connection connection) {
		this.connection = connection;
	}
	
//	public VeiculoDao() {
//		this.connection = new ConnectionFactory().getConnection();
//	}

	public void adiciona(Veiculo veiculo) {
		String sql = "insert into veiculos (renavan,nome,categoria,ano,locado) values (?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, veiculo.getRenavan());
			stmt.setString(2, veiculo.getNome());
			stmt.setString(3, veiculo.getCategoria());
			stmt.setString(4, veiculo.getAno());
			stmt.setBoolean(5, veiculo.isLocado());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Veiculo> getLista() {
		try {
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from veiculos order by nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Veiculo veiculo = new Veiculo();
				veiculo.setRenavan(rs.getString("renavan"));
				veiculo.setNome(rs.getString("nome"));
				veiculo.setCategoria(rs.getString("categoria"));
				veiculo.setAno(rs.getString("ano"));
				veiculo.setLocado(rs.getBoolean("locado"));

				// adicionando o objeto à lista
				veiculos.add(veiculo);
			}
			rs.close();
			stmt.close();
			return veiculos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Veiculo> getDisponivel() {
		try {
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from veiculos where locado like false");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Contato
				Veiculo veiculo = new Veiculo();
				veiculo.setRenavan(rs.getString("renavan"));
				veiculo.setNome(rs.getString("nome"));
				veiculo.setCategoria(rs.getString("categoria"));
				veiculo.setAno(rs.getString("ano"));
				veiculo.setLocado(rs.getBoolean("locado"));

				// adicionando o objeto à lista
				veiculos.add(veiculo);
			}
			rs.close();
			stmt.close();
			return veiculos;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Veiculo> getBusca(String chave) {
		try {
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from veiculos where renavan like ?");

			// // seta os valores
			stmt.setString(1, '%' + chave + '%');
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Veiculo veiculo = new Veiculo();
				veiculo.setRenavan(rs.getString("renavan"));
				veiculo.setNome(rs.getString("nome"));
				veiculo.setCategoria(rs.getString("categoria"));
				veiculo.setAno(rs.getString("ano"));
				veiculo.setLocado(rs.getBoolean("locado"));

				// adicionando o objeto à lista
				veiculos.add(veiculo);
			}
			rs.close();
			stmt.close();
			return veiculos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Veiculo veiculo) {
		String sql = "update veiculos set nome=?, categoria=?, ano=?, locado=? where renavan=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, veiculo.getNome());
			stmt.setString(2, veiculo.getCategoria());
			stmt.setString(3, veiculo.getAno());
			stmt.setBoolean(4, veiculo.isLocado());
			stmt.setString(5, veiculo.getRenavan());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void reservaVeiculo (String renavan) {
		String sql = "update veiculos set locado=1 where renavan=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, renavan);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void devolveVeiculo (String renavan) {
		String sql = "update veiculos set locado=0 where renavan=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, renavan);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Veiculo veiculo) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from veiculos where renavan=?");
			stmt.setString(1, veiculo.getRenavan());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}