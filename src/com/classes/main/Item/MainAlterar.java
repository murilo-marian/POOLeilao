package com.classes.main.Item;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;

public class MainAlterar {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();
        Item novo = new Item("Cachimbo de Prata", 6500);
        Item velho = teste.procurarPorId(3);
        if (teste.alterar(velho, novo)) {
            System.out.println("Alterado");
        }
    }
}
