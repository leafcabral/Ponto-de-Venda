/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minhaempresa.exemplopdv.dao;

import static com.minhaempresa.exemplopdv.dao.VendaDAO.URL;
import static com.minhaempresa.exemplopdv.dao.VendaDAO.login;
import static com.minhaempresa.exemplopdv.dao.VendaDAO.senha;
import com.minhaempresa.exemplopdv.models.RelatorioAnalitico;
import com.minhaempresa.exemplopdv.models.RelatorioSintetico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ftfer
 */
public class RelatorioAnaliticoDAO {

    static String URL = "jdbc:mysql://localhost:3306/exemplopdv";
    static String login = "root";
    static String senha = "adminadmin";
    
    public static ArrayList<RelatorioAnalitico> listarPorVenda(int idVenda){
        Connection conexao = null;
        ResultSet rs = null;
        ArrayList<RelatorioAnalitico> listaRetorno = new ArrayList<>();
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            
            String sql = "SELECT ItemVenda.idProduto, Produto.nomeProduto, ItemVenda.vlrUnitario, ItemVenda.qtdProduto FROM ItemVenda "
                    + "     INNER JOIN Produto ON ItemVenda.idProduto = Produto.idProduto"
                    + "     WHERE idVenda = ?";
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, idVenda  );
            
            rs = comandoSQL.executeQuery();
            
            if(rs !=null){
            
                while(rs.next()){
                
                    RelatorioAnalitico item = null;

                    //int idProduto = rs.getInt("idProduto");
                    String nomeProduto = rs.getString("nomeProduto");
                    float valorUnitario = rs.getFloat("vlrUnitario");
                    int qtdProduto = rs.getInt("qtdProduto");

                    item = new RelatorioAnalitico(nomeProduto, qtdProduto, valorUnitario);

                    listaRetorno.add(item);
                }
                
            }
            
        }catch(SQLException e){
            listaRetorno = null;
        }catch (Exception e) {
            listaRetorno = null;
        }
        
        return listaRetorno;
    }
    
}
