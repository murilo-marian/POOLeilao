package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;
import com.classes.DTO.Pessoa;

public class MainInsercao {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        Pessoa pessoa = new Pessoa("Jorge", 22222222222L, "2003-09-12");
        if (teste.inserir(pessoa)) {
            System.out.println("Inserido com susesso");
        } else {
            System.out.println("Falha ao inserir");
        }
    }
}
