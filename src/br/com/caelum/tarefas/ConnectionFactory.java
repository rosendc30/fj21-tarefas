package br.com.caelum.tarefas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		return DriverManager.getConnection("jdbc:mysql://localhost/fj21",
					"root", "123456");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
