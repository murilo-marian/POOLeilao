package com.classes.main.MainItem;

import com.classes.BO.ItemBO;

public class MainExclusao {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();
        if (teste.excluir(4)) {
            System.out.println("Excluído");
        }
    }
}
