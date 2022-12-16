package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;

public class MainExisteCPF {

    public static void main(String[] args) {
        ClienteBO clienteBO = new ClienteBO();
        System.out.println(clienteBO.existeCpf(44444444444l));
    }
}
