package com.classes.main.MainItem;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;
import com.classes.Util.Util;

public class MainInsercao {
    public static void main(String[] args) {
        Item item = Util.geraItem();
        item.setItemDono(20);
        ItemBO itemBO = new ItemBO();
        itemBO.inserir(item);
    }
}
