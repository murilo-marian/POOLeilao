package com.classes.BO;

import com.classes.DAO.ClienteDAO;
import com.classes.DAO.ClienteDAO;
import com.classes.DTO.Cliente;
import com.classes.DTO.Leiloeiro;
import com.classes.DTO.Pessoa;
import com.classes.Util.Util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ClientInfoStatus;
import java.sql.Date;
import java.util.List;

public class ClienteBO {
    //CREATE
    public boolean inserir(Pessoa novo) {
        Util util = new Util();
        if (util.checaCPF(novo.getCpf())) {
            if (!existeCpf(novo.getCpf())) {
                ClienteDAO clienteDAO = new ClienteDAO();
                return clienteDAO.inserir(novo);
            }
            return false;
        }
        return false;
    }

    //READ
    public Cliente procurarPorId(int id) {
        if (existe(id)) {
            ClienteDAO clienteDAO = new ClienteDAO();

            return clienteDAO.pesquisarID(id);
        }
        return null;
    }

    public List<Cliente> pesquisaNome(String nome) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.pesquisarNome(nome);
    }

    public Cliente pesquisaCPF(long cpf) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.pesquisarCPF(cpf);
    }

    public List<Cliente> pesquisaTudo() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.pesquisaTudo();
    }

    public double puxaOrcamento(int id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return  clienteDAO.puxaOrcamento(id);
    }


    //UPDATE
    public boolean alterar(Cliente cliente, Cliente alterada) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.alterar(cliente, alterada);
    }

    public boolean atualizarFundos(double fundos, int id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.atualizarFundos(fundos, id);
    }


    //DELETE
    public boolean excluir(long cpf) {
        if ((existeCpf(cpf))) {
            ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.excluir(pesquisaCPF(cpf).getId());
        }
        return false;
    }

    //TESTE DE SENHA PARA LOGIN
    public boolean testaSenha(long cpf, String senha) {
        Util util = new Util();
        if (util.checaCPF(cpf)) {
            if (existeCpf(cpf)) {
                ClienteDAO clienteDAO = new ClienteDAO();
                return clienteDAO.testaSenha(cpf, senha);
            }
        }
        return false;
    }


    //EXISTÊNCIAS DE REGISTROS
    public boolean existeCpf(long cpf) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.existeCpf(cpf);
    }

    public boolean existe(int id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.existe(id);
    }
}
