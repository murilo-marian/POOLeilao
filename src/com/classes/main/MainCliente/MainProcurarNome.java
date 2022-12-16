package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;

public class MainProcurarNome {
    public static void main(String[] args) {
        ClienteBO clienteBO = new ClienteBO();
        System.out.println(clienteBO.pesquisaNome("Murilo"));
    }
}
