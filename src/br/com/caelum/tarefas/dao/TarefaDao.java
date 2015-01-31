package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Tarefa;

@Repository
public class TarefaDao {
	private Connection connection;
	private DaoException exception;

	@Autowired
	public TarefaDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "INSERT INTO tarefas (descricao) VALUES (?)";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, tarefa.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			this.exception.getException(e);
		}
	}

	public List<Tarefa> getLista() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM tarefas ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Criando o objeto de tarefa
				Tarefa tarefa = new Tarefa();
				
				tarefa.setId(rs.getLong("id"));
				
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				if (rs.getDate("datafinalizacao") != null) {
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("datafinalizacao"));
					tarefa.setDataFinalizacao(data);
				}

				// Adiciona o tarefas a lista
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			this.exception.getException(e);
		}
		return tarefas;
	}

	public void altera(Tarefa tarefa) {
		String sql = "UPDATE tarefas SET " 
				
				+ "descricao = ?," 
				+ "finalizado = ?," 
				+ "datafinalizacao = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());

			// stmt.setDate(3, new
			// Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			stmt.setDate(3, tarefa.getDataFinalizacao() != null ? new Date(
					tarefa.getDataFinalizacao().getTimeInMillis()) : null);

			stmt.setLong(4, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			this.exception.getException(e);
		}
	}

	public void remove(Long id) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM tarefas WHERE id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			this.exception.getException(e);
		}
	}

	public Tarefa buscaPorId(Long id) {
		Tarefa tarefa = new Tarefa();
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM tarefas WHERE id=?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Criando o objeto de Tarefa
				// Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				Date data = rs.getDate("dataFinalizacao");
				if (data != null) {
					Calendar dataFinalizacao = Calendar.getInstance();
					dataFinalizacao.setTime(data);
					tarefa.setDataFinalizacao(dataFinalizacao);
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			this.exception.getException(e);
		}
		return tarefa;
	}

	public void finaliza(Long id) {
		String sql = "UPDATE tarefas SET " + "finalizado = true,"
				+ "datafinalizacao=? " + "WHERE id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(2, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			this.exception.getException(e);
		}
	}
}