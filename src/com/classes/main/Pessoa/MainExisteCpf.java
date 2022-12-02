package com.classes.main.Pessoa;

import com.classes.BO.PessoaBO;
import com.classes.DAO.PessoaDAO;

public class MainExisteCpf {
    public static void main(String[] args) {
        PessoaBO teste = new PessoaBO();
        if (teste.existeCpf(13201314927L)) {
            System.out.println("Existe");
        }
    }
}
