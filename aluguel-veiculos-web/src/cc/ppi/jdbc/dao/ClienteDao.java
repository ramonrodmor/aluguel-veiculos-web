package cc.ppi.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cc.ppi.jdbc.modelo.Cliente;

public class ClienteDao {

	// a conexão com o banco de dados
	private Connection connection;

	public ClienteDao(Connection connection) {
		this.connection = connection;
	}
	
//	public ClienteDao() {
//		this.connection = new ConnectionFactory().getConnection();
//	}

	public void adiciona(Cliente cliente) {
		String sql = "insert into clientes " + "(cpf,nome,email,endereco,nascimento,telefone)" + " values (?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getEndereco());
			try {
				stmt.setDate(5, new Date(cliente.getDataNascimento().getTimeInMillis()));
			} catch (RuntimeException e) {
				stmt.setDate(5, null);
			}
			stmt.setString(6, cliente.getTelefone());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> getLista() {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from clientes order by nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Cliente cliente = new Cliente();
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));

				// montando a data através do Calendar
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("nascimento"));
					cliente.setDataNascimento(data);
				} catch (RuntimeException e) {
					cliente.setDataNascimento(null);
				}

				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> getBusca(String chave) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from clientes where nome like ? or cpf like ? order by nome");

			// // seta os valores
			stmt.setString(1, '%' + chave + '%');
			stmt.setString(2, '%' + chave + '%');
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Cliente cliente = new Cliente();
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));

				// montando a data através do Calendar
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("nascimento"));
					cliente.setDataNascimento(data);
				} catch (RuntimeException e) {
					cliente.setDataNascimento(null);
				}

				// adicionando o objeto à lista
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Cliente cliente) {
		String sql = "update clientes set nome=?, email=?, endereco=?, nascimento=?, telefone=? where cpf=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getEndereco());
			try {
				stmt.setDate(4, new Date(cliente.getDataNascimento().getTimeInMillis()));
			} catch (RuntimeException e) {
				stmt.setDate(4, null);
			}
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getCpf());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Cliente cliente) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from clientes where cpf=?");
			stmt.setString(1, cliente.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}