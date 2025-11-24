package com.leafcabral.pontoDeVenda.models;

/**
 *
 * @author ftfer
 */
public class RelatorioAnalitico {
    
    private String nomeProduto;
    private int qtdProduto;
    private double vlrUnitario;

    public RelatorioAnalitico(String nomeProduto, int qtdProduto, double vlrUnitario) {
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.vlrUnitario = vlrUnitario;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }
    
    
    
    
}
