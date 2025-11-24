package com.leafcabral.pontoDeVenda.models;

/**
 *
 * @author ftfer
 */
public class Cliente {
    
    private int idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String CPF;
    
    //Alt + Insert
    
    //Construtores

    public Cliente() {
        
    }

    public Cliente(int idCliente, String nomeCliente, String emailCliente, String CPF) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.CPF = CPF;
    }

    public Cliente(String nomeCliente, String emailCliente, String CPF) {
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.CPF = CPF;
    }
    
    //Getters e Setters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
}
