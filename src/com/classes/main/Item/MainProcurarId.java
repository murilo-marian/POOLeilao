package com.classes.main.Item;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;

public class MainProcurarId {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();

        Item item = teste.procurarPorId(4);
        System.out.println(item);
    }
}
