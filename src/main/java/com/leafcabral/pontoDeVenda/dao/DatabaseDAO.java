package com.leafcabral.pontoDeVenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseDAO {
	protected static String URL = "jdbc:mysql://localhost:3306/exemplopdv";
	protected static String login = "root";
	protected static String senha = "adminadmin";
	protected static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public static void setDatabaseCredentials(String url, String user, String password) {
		URL = url;
		login = user;
		senha = password;
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