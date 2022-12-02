package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;
import com.classes.DTO.Pessoa;

public class MainAlteracao {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        Pessoa pessoa = teste.procurarPorId(2);
        Pessoa alterada = pessoa;
        alterada.setNome("Jorge");
        if (teste.alterar(pessoa, alterada)) {
            System.out.println("Alterado com susesso");
        } else {
            System.out.println("Falha ao alterar");
        }
    }
}
