package com.classes.BO;

import com.classes.DAO.LeiloeiroDAO;
import com.classes.DTO.Leiloeiro;
import com.classes.DTO.Pessoa;
import com.classes.Util.Util;

import java.util.List;

public class LeiloeiroBO {
    //CREATE
    public boolean inserir(Pessoa novo) {
        Util util = new Util();
        if (util.checaCPF(novo.getCpf())) {
            if (!existeCpf(novo.getCpf())) {
                LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
                return leiloeiroDAO.inserir(novo);
            }
            return false;
        }
        return false;
    }

    //READ
    public Leiloeiro procurarPorId(int id) {
        if (existe(id)) {
            LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();

            return leiloeiroDAO.pesquisarID(id);
        }
        return null;
    }

    public List<Leiloeiro> pesquisaNome(String nome) {
        LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
        return leiloeiroDAO.pesquisarNome(nome);
    }

    public Leiloeiro pesquisaCPF(long cpf) {
        LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
        return leiloeiroDAO.pesquisarCPF(cpf);
    }

    public List<Leiloeiro> pesquisaTudo() {
        LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
        return leiloeiroDAO.pesquisaTudo();
    }


    //UPDATE
    public boolean alterar(Leiloeiro leiloeiro, Leiloeiro alterada) {
        LeiloeiroDAO pessoaDao = new LeiloeiroDAO();
        return pessoaDao.alterar(leiloeiro, alterada);
    }


    //DELETE
    public boolean excluir(long cpf) {
        if ((existeCpf(cpf))) {
            LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
            return  leiloeiroDAO.excluir(pesquisaCPF(cpf).getId());
        }
        return false;
    }

    //TESTE DE SENHA PARA LOGIN
    public boolean testaSenha(long cpf, String senha) {
            Util util = new Util();
            if (util.checaCPF(cpf)) {
                if (existeCpf(cpf)) {
                    LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
                    return leiloeiroDAO.testaSenha(cpf, senha);
                }
            }
        return false;
    }


    //EXISTÊNCIAS DE REGISTROS
    public boolean existeCpf(long cpf) {
        LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
        return leiloeiroDAO.existeCpf(cpf);
    }

    public boolean existe(int id) {
        LeiloeiroDAO leiloeiroDAO = new LeiloeiroDAO();
        return leiloeiroDAO.existe(id);
    }
}
