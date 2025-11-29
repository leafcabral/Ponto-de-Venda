package com.leafcabral.pontoDeVenda.dao;

import com.leafcabral.pontoDeVenda.models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO extends DatabaseDAO {
	
	public static boolean salvar(Cliente obj){
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		boolean retorno = false;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"INSERT INTO cliente (nomeCliente, cpf, emailCliente) VALUES (?,?,?);"
			);
			
			instrucaoSQL.setString(1, obj.getNomeCliente());
			instrucaoSQL.setString(2, obj.getCPF());
			instrucaoSQL.setString(3, obj.getEmailCliente());
			
			int linhasAfetadas = instrucaoSQL.executeUpdate();
			
			if(linhasAfetadas>0){
				retorno = true;
			}
			
		} catch(Exception e){	
			System.out.println(e.getMessage());			
		} finally {
			closeAll(conexao, instrucaoSQL, null);
		}
		return retorno;
	}
	
	public static ArrayList<Cliente> listar(){
		ArrayList<Cliente> listaRetorno = new ArrayList<>();
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		ResultSet rs = null;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"SELECT * FROM Cliente"
			);
			
			rs = instrucaoSQL.executeQuery();
			
			if(rs !=null){
				
				while(rs.next()){
					
					int id = rs.getInt("idCliente");
					String nome = rs.getString("nomeCliente");
					String CPF = rs.getString("CPF");
					String email = rs.getString("emailCliente");
					
					Cliente item = new Cliente(id, nome, email, CPF);
					listaRetorno.add(item);
				}
			
			}
			
		} catch (Exception e) {
			listaRetorno = null;
		} finally {
			closeAll(conexao, instrucaoSQL, rs);
		}

		return listaRetorno;
		
	}
	
   public static Cliente buscarPorCPF(String cpfBuscar){
	   Cliente retorno = null;
	   Connection conexao = null;
	   PreparedStatement instrucaoSQL = null;
	   ResultSet rs = null;
	   
	   try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"SELECT * FROM Cliente WHERE CPF = ?"
			);
			
			instrucaoSQL.setString(1, cpfBuscar);
			
			rs = instrucaoSQL.executeQuery();
			
			if(rs !=null){
				
				while(rs.next()){
					int id = rs.getInt("idCliente");
					String nome = rs.getString("nomeCliente");
					String cpf = rs.getString("CPF");
					String email = rs.getString("emailCliente");
					
					retorno = new Cliente(id, nome, email, cpf);
				}
				
			}
	   
		}catch(Exception e){
			retorno = null;
		} finally {
			closeAll(conexao, instrucaoSQL, rs);
		}
		 
		return retorno;
   
   }
	
	public static boolean alterar(Cliente obj){
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		boolean retorno = false;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"UPDATE cliente SET nomeCliente=?, emailCliente=? WHERE idCliente = ?;"
			);
			
			instrucaoSQL.setString(1, obj.getNomeCliente());
			instrucaoSQL.setString(2, obj.getEmailCliente());
			instrucaoSQL.setInt(3, obj.getIdCliente());
			
			int linhasAfetadas = instrucaoSQL.executeUpdate();
			
			if(linhasAfetadas>0){
				retorno = true;
			}
			
		} catch(Exception e){	
			System.out.println(e.getMessage());			
		} finally {
			closeAll(conexao, instrucaoSQL, null);
		}
		return retorno;
	}
	
	public static boolean excluir(int idExcluir){
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		boolean retorno = false;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"DELETE FROM Cliente WHERE idCliente = ?;"
			);
			
			instrucaoSQL.setInt(1, idExcluir);
			
			int linhasAfetadas = instrucaoSQL.executeUpdate();
			
			if(linhasAfetadas>0){
				retorno = true;
			}
			
		} catch(Exception e){	
			System.out.println(e.getMessage());			
		} finally {
			closeAll(conexao, instrucaoSQL, null);
		}
		return retorno;
	}
	
}