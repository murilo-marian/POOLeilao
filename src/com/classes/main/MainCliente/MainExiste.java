package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;

public class MainExiste {
    public static void main(String[] args) {
        ClienteBO clienteBO = new ClienteBO();
        System.out.println(clienteBO.existe(20));
    }
}
