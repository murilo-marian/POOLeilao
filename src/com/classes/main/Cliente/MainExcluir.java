package com.classes.main.Cliente;

import com.classes.BO.PessoaBO;

public class MainExcluir {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        teste.excluir(13201314927L);
    }
}
