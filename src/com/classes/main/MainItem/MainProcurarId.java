package com.classes.main.MainItem;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;

public class MainProcurarId {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();

        Item item = teste.procurarPorId(5);
        System.out.println(item);
    }
}
