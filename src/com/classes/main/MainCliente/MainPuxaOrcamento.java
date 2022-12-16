package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;

public class MainPuxaOrcamento {
    public static void main(String[] args) {
        ClienteBO clienteBO = new ClienteBO();
        System.out.println(clienteBO.puxaOrcamento(20));
    }

}
