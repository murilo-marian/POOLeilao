package com.classes.main.Item;

import com.classes.BO.ItemBO;
import com.classes.DTO.Item;

import java.util.ArrayList;
import java.util.List;

public class MainPesquisarTudo {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();

        List<Item> itens = new ArrayList<>();
        itens = teste.pesquisarTudo();
        System.out.println(itens);
    }
}
