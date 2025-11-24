package com.leafcabral.pontoDeVenda.models;

/**
 *
 * @author ftfer
 */
public class ItemVenda {
    
    private int idItemVenda;
    private int idVenda;
    private int idProduto;
    private int qtdProduto;
    private float vlrUnitario;
    
    //ALT + INSERT

    public ItemVenda(int idProduto, int qtdProduto, float vlrUnitario) {
        this.idProduto = idProduto;
        this.qtdProduto = qtdProduto;
        this.vlrUnitario = vlrUnitario;
    }
    
    

    public ItemVenda(int idVenda, int idProduto, int qtdProduto, float vlrUnitario) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.qtdProduto = qtdProduto;
        this.vlrUnitario = vlrUnitario;
    }

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public float getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(float vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }
    
    
    
}
