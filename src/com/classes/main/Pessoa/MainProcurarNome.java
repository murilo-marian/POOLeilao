package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;
import com.classes.DTO.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainProcurarNome {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        List<Pessoa> pessoa = new ArrayList<>();
        System.out.println(teste.procurarPorNome("Murilo"));
    }
}
