package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;

public class MainExcluir {
    public static void main(String[] args) {
        ClienteBO clienteBO = new ClienteBO();
        clienteBO.excluir(13201314927l);
        System.out.println(clienteBO.pesquisaCPF(13201314927l));
    }
}
