package com.leafcabral.pontoDeVenda.dao;

import com.leafcabral.pontoDeVenda.models.RelatorioAnalitico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RelatorioAnaliticoDAO extends DatabaseDAO {
	
	public static ArrayList<RelatorioAnalitico> listarPorVenda(int idVenda){
		Connection conexao = null;
		PreparedStatement comandoSQL = null;
		ResultSet rs = null;
		ArrayList<RelatorioAnalitico> listaRetorno = new ArrayList<>();
		
		try {
			
			conexao = getConnection();
			
			String sql = "SELECT ItemVenda.idProduto, Produto.nomeProduto, ItemVenda.vlrUnitario, ItemVenda.qtdProduto FROM ItemVenda "
					+ "	 INNER JOIN Produto ON ItemVenda.idProduto = Produto.idProduto"
					+ "	 WHERE idVenda = ?";
			comandoSQL = conexao.prepareStatement(sql);
			comandoSQL.setInt(1, idVenda  );
			
			rs = comandoSQL.executeQuery();
			
			if(rs !=null){
			
				while(rs.next()){
				
					RelatorioAnalitico item = null;

					String nomeProduto = rs.getString("nomeProduto");
					float valorUnitario = rs.getFloat("vlrUnitario");
					int qtdProduto = rs.getInt("qtdProduto");

					item = new RelatorioAnalitico(nomeProduto, qtdProduto, valorUnitario);

					listaRetorno.add(item);
				}
				
			}
			
		}catch(Exception e){
			listaRetorno = null;
		} finally {
			closeAll(conexao, comandoSQL, rs);
		}
		
		return listaRetorno;
	}
	
}