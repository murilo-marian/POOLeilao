package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;

public class MainPesquisaTudo {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        System.out.println(teste.pesquisarTudo());
    }
}
