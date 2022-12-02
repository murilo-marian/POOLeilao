package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;
import com.classes.DTO.Pessoa;

public class MainProcurarId {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        Pessoa pessoa = new Pessoa();
        pessoa = teste.procurarPorId(1);
        System.out.println(pessoa);
    }
}
