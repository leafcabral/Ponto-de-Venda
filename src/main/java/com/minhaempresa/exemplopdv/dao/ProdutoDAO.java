/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minhaempresa.exemplopdv.dao;

import static com.minhaempresa.exemplopdv.dao.ClienteDAO.URL;
import static com.minhaempresa.exemplopdv.dao.ClienteDAO.login;
import static com.minhaempresa.exemplopdv.dao.ClienteDAO.senha;
import com.minhaempresa.exemplopdv.models.Cliente;
import com.minhaempresa.exemplopdv.models.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ftfer
 */
public class ProdutoDAO {
    static String URL = "jdbc:mysql://localhost:3306/exemplopdv";
    static String login = "root";
    static String senha = "adminadmin";
    
    
    public static boolean salvar(Produto obj){
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //1) Carregar o driver o mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2) Fazer conexão com o banco
            conexao = DriverManager.getConnection(URL, login,senha);
            
            //3) Preparar o comando SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO Produto (codProduto, nomeProduto, qtdProduto, valorProduto) VALUES (?,?,?,?);"
            );
            
            instrucaoSQL.setInt(1, obj.getCodProduto());
            instrucaoSQL.setString(2, obj.getNomeProduto());
            instrucaoSQL.setInt(3, obj.getQtdProduto());
            instrucaoSQL.setFloat(4, obj.getValorProduto());
            
            //4) Executar o comando 
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0){
                retorno = true;
            }
            
        } catch(ClassNotFoundException e){    
            System.out.println("Driver não encontrado");            
        } catch (Exception e) {
             System.out.println(e.getMessage()); 
        } finally {
            if(conexao!=null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
    
    public static ArrayList<Produto> listar(){
        ArrayList<Produto> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            //1) Carregar o driver o mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2) Fazer conexão com o banco
            conexao = DriverManager.getConnection(URL, login,senha);
            
            //3) Preparar o comando SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "SELECT * FROM Produto;"
            );
            
            //4) Executar o comando SQL
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
            
            if(conexao!= null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }

        return listaRetorno;
        
    }
    
    public static boolean alterar(Produto obj){
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //1) Carregar o driver o mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2) Fazer conexão com o banco
            conexao = DriverManager.getConnection(URL, login,senha);
            
            //3) Preparar o comando SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "UPDATE produto SET codProduto=?, nomeProduto=?, valorProduto=?, qtdProduto=?  WHERE idProduto = ?;"
            );
            
            instrucaoSQL.setInt(1, obj.getCodProduto());
            instrucaoSQL.setString(2, obj.getNomeProduto());
            instrucaoSQL.setFloat(3, obj.getValorProduto());
            instrucaoSQL.setInt(4, obj.getQtdProduto());
            instrucaoSQL.setInt(5, obj.getIdProduto());
            
            //4) Executar o comando 
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0){
                retorno = true;
            }
            
        } catch(ClassNotFoundException e){    
            System.out.println("Driver não encontrado");            
        } catch (Exception e) {
             System.out.println(e.getMessage()); 
        } finally {
            if(conexao!=null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
    
    public static boolean excluir(int idExcluir){
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            
            //1) Carregar o driver o mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2) Fazer conexão com o banco
            conexao = DriverManager.getConnection(URL, login,senha);
            
            //3) Preparar o comando SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "DELETE FROM Produto WHERE idProduto = ?;"
            );
            
            instrucaoSQL.setInt(1, idExcluir);
            
            //4) Executar o comando 
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0){
                retorno = true;
            }
            
        } catch(ClassNotFoundException e){    
            System.out.println("Driver não encontrado");            
        } catch (Exception e) {
             System.out.println(e.getMessage()); 
        } finally {
            if(conexao!=null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
    
    public static Produto buscarPorCodigo(int codigo){
        Produto retorno = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            //1) Carregar o driver o mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2) Fazer conexão com o banco
            conexao = DriverManager.getConnection(URL, login,senha);
            
            //3) Preparar o comando SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "SELECT * FROM Produto WHERE codProduto = ?"
            );
            
            instrucaoSQL.setInt(1, codigo);
            
            
            //4) Executar o comando SQL
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
            
            if(conexao!= null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }

        return retorno;
        
    }
    
}
