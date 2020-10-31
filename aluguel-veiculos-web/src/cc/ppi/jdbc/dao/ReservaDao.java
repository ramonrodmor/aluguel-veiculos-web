package cc.ppi.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cc.ppi.jdbc.modelo.Reserva;

public class ReservaDao {

	// a conexão com o banco de dados
	private Connection connection;

	public ReservaDao(Connection connection) {
		this.connection = connection;
	}

//	public ReservaDao() {
//		this.connection = new ConnectionFactory().getConnection();
//	}

	public void adiciona(Reserva reserva) {
		String sql = "insert into reservas (codigo,renavan,cpf,rdata,rhora,ddata,dhora,valor) values (?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, reserva.getCodigo());
			stmt.setString(2, reserva.getRenavan());
			stmt.setString(3, reserva.getCpf());
			stmt.setDate(4, new Date(reserva.getRdata().getTimeInMillis()));
			stmt.setString(5, reserva.getRhora());
			stmt.setDate(6, new Date(reserva.getDdata().getTimeInMillis()));
			stmt.setString(7, reserva.getDhora());
			stmt.setFloat(8, reserva.getValor());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> getLista() {
		try {
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from reservas order by codigo desc");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Reserva reserva = new Reserva();
				reserva.setCodigo(rs.getString("codigo"));
				reserva.setCpf(rs.getString("cpf"));
				reserva.setRenavan(rs.getString("renavan"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("rdata"));
					reserva.setRdata(data);
				} catch (RuntimeException e) {
					reserva.setRdata(null);
				}
				reserva.setRhora(rs.getString("rhora"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("ddata"));
					reserva.setDdata(data);
				} catch (RuntimeException e) {
					reserva.setDdata(null);
				}
				reserva.setDhora(rs.getString("dhora"));
				reserva.setValor(rs.getFloat("valor"));

				// adicionando o objeto à lista
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> getBusca(String chave) {
		try {
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from reservas where cpf like ? or renavan like ? order by codigo desc");

			// // seta os valores
			stmt.setString(1, '%' + chave + '%');
			stmt.setString(2, '%' + chave + '%');
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Reserva reserva = new Reserva();
				reserva.setCodigo(rs.getString("codigo"));
				reserva.setCpf(rs.getString("cpf"));
				reserva.setRenavan(rs.getString("renavan"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("rdata"));
					reserva.setRdata(data);
				} catch (RuntimeException e) {
					reserva.setRdata(null);
				}
				reserva.setRhora(rs.getString("rhora"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("ddata"));
					reserva.setDdata(data);
				} catch (RuntimeException e) {
					reserva.setDdata(null);
				}
				reserva.setDhora(rs.getString("dhora"));
				reserva.setValor(rs.getFloat("valor"));

				// adicionando o objeto à lista
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> getBuscaCodigo(String chave) {
		try {
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from reservas where codigo like ?");

			// // seta os valores
			stmt.setString(1, chave);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				// criando o objeto Contato
				Reserva reserva = new Reserva();
				reserva.setCodigo(rs.getString("codigo"));
				reserva.setCpf(rs.getString("cpf"));
				reserva.setRenavan(rs.getString("renavan"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("rdata"));
					reserva.setRdata(data);
				} catch (RuntimeException e) {
					reserva.setRdata(null);
				}
				reserva.setRhora(rs.getString("rhora"));
				try {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("ddata"));
					reserva.setDdata(data);
				} catch (RuntimeException e) {
					reserva.setDdata(null);
				}
				reserva.setDhora(rs.getString("dhora"));
				reserva.setValor(rs.getFloat("valor"));

				// adicionando o objeto à lista
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Reserva reserva) {
		String sql = "update reservas set renavan=?, cpf=?, rdata=?, rhora=?, ddata=?, dhora=?, valor=? where codigo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, reserva.getRenavan());
			stmt.setString(2, reserva.getCpf());
			stmt.setDate(3, new Date(reserva.getRdata().getTimeInMillis()));
			stmt.setString(4, reserva.getRhora());
			stmt.setDate(5, new Date(reserva.getDdata().getTimeInMillis()));
			stmt.setString(6, reserva.getDhora());
			stmt.setFloat(7, reserva.getValor());
			stmt.setString(8, reserva.getCodigo());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Reserva reserva) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from reservas where codigo=?");
			stmt.setString(1, reserva.getCodigo());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}