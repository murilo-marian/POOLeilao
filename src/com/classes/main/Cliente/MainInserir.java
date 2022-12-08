package com.classes.main.Cliente;

import com.classes.BO.PessoaBO;
import com.classes.DAO.PessoaDAO;
import com.classes.DTO.Cliente;
import com.classes.DTO.Pessoa;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainInserir {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PessoaBO teste = new PessoaBO();
        /*teste.excluir(13201314927L);*/
        Cliente Jorge = new Cliente("Jorginho", 13201314927L, "2003-12-12");
        teste.inserir(Jorge, "hunter2");
        System.out.println(teste.testaSenha(13201314927L, "hunter2"));

    }
}
