package com.leafcabral.pontoDeVenda.dao;

import com.leafcabral.pontoDeVenda.models.RelatorioSintetico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class RelatorioSinteticoDAO extends DatabaseDAO {
	
	public static ArrayList<RelatorioSintetico> listarPorPeriodo(Date dtInicio, Date dtTermino){
		Connection conexao = null;
		PreparedStatement comandoSQL = null;
		ResultSet rs = null;
		ArrayList<RelatorioSintetico> listaRetorno = new ArrayList<>();
		
		try {
			
			conexao = getConnection();
			
			String sql = "SELECT Venda.idVenda, Venda.idCliente, Cliente.nomeCliente, Venda.dataVenda, Venda.valorVenda FROM Venda "
					+ "	 INNER JOIN Cliente ON Venda.idCliente = Cliente.idCliente";
			
			if (dtInicio != null || dtTermino != null) {
			    sql += " WHERE ";

			    if (dtInicio != null && dtTermino != null) {
				sql += "dataVenda >= ? AND dataVenda <= ?";
			    } else if (dtInicio != null) {
				sql += "dataVenda >= ?";
			    } else if (dtTermino != null) {
				sql += "dataVenda <= ?";
			    }
			}
			sql += (" ORDER BY Venda.dataVenda DESC");
        
			comandoSQL = conexao.prepareStatement(sql);
			int paramIndex = 1;
			if (dtInicio != null && dtTermino != null) {
				comandoSQL.setDate(paramIndex++, new java.sql.Date(dtInicio.getTime()));
				comandoSQL.setDate(paramIndex++, new java.sql.Date(dtTermino.getTime()));
			} else if (dtInicio != null) {
				comandoSQL.setDate(paramIndex++, new java.sql.Date(dtInicio.getTime()));
			} else if (dtTermino != null) {
				comandoSQL.setDate(paramIndex++, new java.sql.Date(dtTermino.getTime()));
			}
			
			rs = comandoSQL.executeQuery();
			
			if(rs !=null){
			
				while(rs.next()){
				
					RelatorioSintetico item = null;

					int idVenda = rs.getInt("idVenda");
					int idCliente = rs.getInt("idCliente");
					String nomeCliente = rs.getString("nomeCliente");
					Date dataVenda = rs.getDate("dataVenda");
					float valorVenda = rs.getFloat("valorVenda");

					item = new RelatorioSintetico(idVenda, dataVenda, idCliente, nomeCliente, valorVenda);

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