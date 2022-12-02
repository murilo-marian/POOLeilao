package com.classes.main.Item;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;

public class MainInsercao {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();
        Item item = new Item("Cachimbo de Ouro", 34500.00);
        if (teste.inserir(item)) {
            System.out.println("Inserido");
        }
    }
}
