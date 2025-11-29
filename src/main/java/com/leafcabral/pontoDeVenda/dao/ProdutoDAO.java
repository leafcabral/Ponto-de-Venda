package com.leafcabral.pontoDeVenda.dao;

import com.leafcabral.pontoDeVenda.models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO extends DatabaseDAO {
	
	public static boolean salvar(Produto obj){
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		boolean retorno = false;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"INSERT INTO Produto (codProduto, nomeProduto, qtdProduto, valorProduto) VALUES (?,?,?,?);"
			);
			
			instrucaoSQL.setInt(1, obj.getCodProduto());
			instrucaoSQL.setString(2, obj.getNomeProduto());
			instrucaoSQL.setInt(3, obj.getQtdProduto());
			instrucaoSQL.setFloat(4, obj.getValorProduto());
			
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
	
	public static ArrayList<Produto> listar(){
		ArrayList<Produto> listaRetorno = new ArrayList<>();
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		ResultSet rs = null;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"SELECT * FROM Produto;"
			);
			
			rs = instrucaoSQL.executeQuery();
			
			if(rs !=null){
				
				while(rs.next()){
					
					int id = rs.getInt("idProduto");
					int codProduto = rs.getInt("codProduto");
					int qtdProduto = rs.getInt("qtdProduto");
					String nome = rs.getString("nomeProduto");
					float valor = rs.getFloat("valorProduto");
					
					Produto item = new Produto(id, codProduto, nome, qtdProduto, valor);
					
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
	
	public static boolean alterar(Produto obj){
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		boolean retorno = false;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"UPDATE produto SET codProduto=?, nomeProduto=?, valorProduto=?, qtdProduto=?  WHERE idProduto = ?;"
			);
			
			instrucaoSQL.setInt(1, obj.getCodProduto());
			instrucaoSQL.setString(2, obj.getNomeProduto());
			instrucaoSQL.setFloat(3, obj.getValorProduto());
			instrucaoSQL.setInt(4, obj.getQtdProduto());
			instrucaoSQL.setInt(5, obj.getIdProduto());
			
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
					"DELETE FROM Produto WHERE idProduto = ?;"
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
	
	public static Produto buscarPorCodigo(int codigo){
		Produto retorno = null;
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		ResultSet rs = null;
		
		try {
			conexao = getConnection();
			
			instrucaoSQL = conexao.prepareStatement(
					"SELECT * FROM Produto WHERE codProduto = ?"
			);
			
			instrucaoSQL.setInt(1, codigo);
			
			rs = instrucaoSQL.executeQuery();
			
			if(rs !=null){
				
				while(rs.next()){
					
					int id = rs.getInt("idProduto");
					String nome = rs.getString("nomeProduto");
					int cod = rs.getInt("codProduto");
					int qtd = rs.getInt("qtdProduto");
					float valor = rs.getFloat("valorProduto");
					
					retorno = new Produto(id, codigo, nome, qtd, valor);
				}
			
			}
			
		} catch (Exception e) {
			retorno = null;
		} finally {
			closeAll(conexao, instrucaoSQL, rs);
		}

		return retorno;
		
	}
	
}