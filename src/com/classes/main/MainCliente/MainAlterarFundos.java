package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;

public class MainAlterarFundos {
    public static void main(String[] args) {
        ClienteBO clienteBO = new ClienteBO();
        clienteBO.atualizarFundos(200000, 20);
        System.out.println(clienteBO.procurarPorId(20));
    }
}
