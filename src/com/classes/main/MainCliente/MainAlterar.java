package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;
import com.classes.DTO.Cliente;

public class MainAlterar {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Murilo", 13201314927l, "2003-09-12");

        ClienteBO clienteBO = new ClienteBO();
        Cliente velho = clienteBO.procurarPorId(20);
        clienteBO.alterar(velho, cliente);
        System.out.println(clienteBO.procurarPorId(4));
    }
}
