package com.classes.main.MainCliente;

import com.classes.BO.ClienteBO;
import com.classes.DTO.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MainPesquisaTudo {
    public static void main(String[] args) {
        ClienteBO teste = new ClienteBO();

        List<Cliente> clientes = new ArrayList<>();
        clientes = teste.pesquisaTudo();
        System.out.println(clientes);
    }
}
