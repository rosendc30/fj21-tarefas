package br.com.caelum.tarefas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		return DriverManager.getConnection("jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT//fj21tarefas",
					"adminaDbbics", "Fpg7M_UIKc4D");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
