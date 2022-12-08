package com.classes.DTO;

import java.sql.Date;

public class Cliente extends Pessoa{
    private int idCliente;
    private double orcamento;
    private boolean isAdmin;

    public Cliente(String nome, long cpf, String dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(Date.valueOf(dataNascimento));
    }
    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
