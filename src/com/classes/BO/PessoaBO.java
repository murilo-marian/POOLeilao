package com.classes.BO;

import com.classes.DAO.PessoaDAO;
import com.classes.DTO.Pessoa;

import java.util.List;

public class PessoaBO {
    public boolean inserir(Pessoa pessoa) {
        if (!existeCpf(pessoa.getCpf())) {
            if (checaCPF(pessoa.getCpf())) {
                PessoaDAO pessoaDAO = new PessoaDAO();
                return pessoaDAO.inserir(pessoa);

            }
            return false;
        }
        return false;
    }

    public boolean checaCPF(long cpf) {
        int[] cpfSeparado = cpfPraArray(cpf);
        int somaUm = 0;
        for (int i = 1, j = 10; i < 10; i++, j--) {
            somaUm += j * cpfSeparado[i];
        }
        somaUm = 11 - (somaUm % 11);

        int somaDois = 0;
        for (int i = 1, j = 11; i < 11; i++, j--) {
            somaDois += j * cpfSeparado[i];
        }
        somaDois = 11 - (somaDois % 11);
        if (((somaUm == cpfSeparado[10]) || (somaUm > 10 && cpfSeparado[10] == 0)) && ((somaDois == cpfSeparado[11]) || (somaDois > 10 && cpfSeparado[11] == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public int[] cpfPraArray(long cpf) {
        String cpfString = Long.toString(cpf);
        int[] cpfArray = new int[12];
        for (int i = 11; i > 0; i--) {
            cpfArray[i] = Integer.parseInt(String.valueOf(cpfString.charAt(i-1)));
        }
        return cpfArray;
    }

    public boolean alterar(Pessoa pessoa, Pessoa alterada) {
            PessoaDAO pessoaDao = new PessoaDAO();
            return pessoaDao.alterar(pessoa, alterada);
    }

    public boolean excluir(Pessoa pessoa) {
        if ((existeCpf(pessoa.getCpf()))) {
            PessoaDAO pessoaDAO = new PessoaDAO();
            return  pessoaDAO.excluir(pessoa.getId());
        }
        return false;
    }

    public Pessoa procurarPorId(int id) {
        if (existe(id)) {
            PessoaDAO pessoaDAO = new PessoaDAO();
            return pessoaDAO.procurarPorId(id);
        }
        return null;
    }

    public List<Pessoa> procurarPorNome(String nome) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.procurarPorNome(nome);
    }

    public List<Pessoa> pesquisarTudo() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return  pessoaDAO.pesquisarTodos();
    }
    public boolean existeCpf(long cpf) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.existeCpf(cpf);
    }

    public boolean existe(int id) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.existe(id);
    }
}
