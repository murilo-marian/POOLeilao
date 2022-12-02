package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;
import com.classes.DTO.Pessoa;

public class MainExclusao {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        Pessoa pessoa = teste.procurarPorId(3);
        if (teste.excluir(pessoa)) {
            System.out.println("Excluído com susesso");
        } else {
            System.out.println("Falha ao excluir");
        }
    }
}
