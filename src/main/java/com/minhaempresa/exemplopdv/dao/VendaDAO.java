package com.minhaempresa.exemplopdv.dao;

import com.minhaempresa.exemplopdv.models.ItemVenda;
import com.minhaempresa.exemplopdv.models.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ftfer
 */
public class VendaDAO {
    
    static String URL = "jdbc:mysql://localhost:3306/exemplopdv";
    static String login = "root";
    static String senha = "adminadmin";
    
    public static boolean salvar(Venda obj){
        Connection conexao = null;
        boolean retorno = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            
            String sql = "INSERT INTO Venda (dataVenda, valorVenda, idCliente) VALUES (?,?,?)";
            PreparedStatement comandoSQL = conexao.prepareStatement(sql
                                    , PreparedStatement.RETURN_GENERATED_KEYS);
            
            comandoSQL.setDate(1, new java.sql.Date(obj.getDataVenda().getTime()) );
            comandoSQL.setFloat(2, obj.getValorVenda());
            comandoSQL.setInt(3, obj.getIdCliente());
            
            int linhasAfetadas = comandoSQL.executeUpdate();
            
            if(linhasAfetadas>0){
                ResultSet rs = comandoSQL.getGeneratedKeys();
                
                if(rs.next()){
                    int id = rs.getInt(1);
                    
                    for(ItemVenda item: obj.getListaItens()){
                        String sql2 = "INSERT INTO ItemVenda (idVenda, idProduto, qtdProduto, vlrUnitario) VALUES(?, ?, ?, ?)";
                        PreparedStatement comandoSQL2 = conexao.prepareStatement(sql2);
                        
                        comandoSQL2.setInt(1, id);
                        comandoSQL2.setInt(2, item.getIdProduto());
                        comandoSQL2.setInt(3, item.getQtdProduto());
                        comandoSQL2.setFloat(4, item.getVlrUnitario());
                        
                        int linhas = comandoSQL2.executeUpdate();
                        
                        if(linhas > 0){
                            retorno = true;
                        }
                    
                    }
                    
                    
                }else{
                    throw new Exception("Falha ao criar venda!");
                }
                
            }
            
            
        } catch (Exception e) {
            
        }
        
    
        return retorno;
    }
    
}
