package com.classes.DTO;

import com.classes.BO.ClienteBO;
import com.classes.BO.ItemBO;
import com.classes.ILogin;

import java.sql.Date;

public class Cliente extends Pessoa implements ILogin {
    private double orcamento;

    public Cliente(String nome, long cpf, String dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(Date.valueOf(dataNascimento));
    }
    public Cliente() {
    }

    @Override
    public boolean logar(long cpf, String senha) {
        ClienteBO clienteBO = new ClienteBO();
        Cliente cliente = new Cliente();
        if (clienteBO.testaSenha(cpf, senha)) {
            return true;
        }

        return false;
    }

    public boolean registrar(Pessoa novo) {
        ClienteBO clienteBO = new ClienteBO();
        return clienteBO.inserir(novo);
    }

    public boolean vender(int id) {
        ItemBO itemBO = new ItemBO();
        ClienteBO clienteBO = new ClienteBO();
        if (itemBO.existe(id)) {
            Item vendido = itemBO.procurarPorId(id);
            clienteBO.atualizarFundos(vendido.getItemValor(), this.getId());
            itemBO.excluir(id);
            return true;
        } else return false;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }
}
