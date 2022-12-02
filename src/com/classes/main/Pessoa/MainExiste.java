package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;

public class MainExiste {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        if (teste.existe(7)) {
            System.out.println("Existe");
        }
    }
}
