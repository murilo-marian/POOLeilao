package com.classes.main.MainItem;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;
import com.classes.Util.Util;

public class MainAlterar {
    public static void main(String[] args) {
        Item item = Util.geraItem();

        ItemBO itemBO = new ItemBO();
        Item velho = itemBO.procurarPorId(4);
        itemBO.alterar(velho, item);
        System.out.println(itemBO.procurarPorId(4));
    }
}
