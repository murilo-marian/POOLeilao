package com.classes.main.MainItem;

import com.classes.BO.ItemBO;

public class MainProcurarNome {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();

        System.out.println(teste.procurarPorNome("Fivela de Cinto de Prata de Franklin Roosevelt"));
    }
}
