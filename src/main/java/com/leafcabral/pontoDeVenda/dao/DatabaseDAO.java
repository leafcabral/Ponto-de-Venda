package com.leafcabral.pontoDeVenda.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;

public abstract class DatabaseDAO {
	protected static String database = "exemplopdv";
	protected static String URL = "jdbc:mysql://localhost:3306/";
	protected static String login = "root";
	protected static String senha = "adminadmin";
	protected static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public static void setDatabaseCredentials(String url, String user, String password) {
		URL = url;
		login = user;
		senha = password;
	}
	public static void setDatabase() { URL += database; }
	
	public static boolean databaseExists() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		PreparedStatement sql = connection.prepareStatement(
			"SHOW DATABASES LIKE ?"
		);
		sql.setString(1, database);

		ResultSet rs = sql.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	public static void createDatabase() throws SQLException, ClassNotFoundException, IOException {
		Connection connection = getConnection();
		InputStream file = getResourceAsStream("/DumpBancoPDV.sql");
		
		String sql = new Scanner(file).next();
		connection.createStatement().execute(sql);
	}
	
	protected static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, login, senha);
	}
	protected static void closeConnection(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar conexão: " + ex.getMessage());
			}
		}
	}
	
	protected static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar ResultSet: " + ex.getMessage());
			}
		}
	}
	protected static void closeStatement(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Erro ao fechar PreparedStatement: " + ex.getMessage());
			}
		}
	}
	protected static void closeAll(Connection conexao, PreparedStatement stmt, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conexao);
	}

	public static boolean testConnection() {
		Connection conexao = null;
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, login, senha);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (conexao != null) {
				try { conexao.close(); }
				catch (SQLException ex) { }
			}
		}
	}
}