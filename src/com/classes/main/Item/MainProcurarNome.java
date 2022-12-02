package com.classes.main.Item;

import com.classes.BO.ItemBO;

public class MainProcurarNome {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();

        System.out.println(teste.procurarPorNome("Cachimbo de Ouro"));
    }
}
