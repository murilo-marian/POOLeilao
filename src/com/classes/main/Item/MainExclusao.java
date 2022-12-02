package com.classes.main.Item;

import com.classes.BO.ItemBO;

public class MainExclusao {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();
        if (teste.excluir(3)) {
            System.out.println("Excluído");
        }
    }
}
