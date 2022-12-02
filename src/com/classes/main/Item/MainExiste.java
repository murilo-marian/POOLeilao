package com.classes.main.Item;

import com.classes.BO.ItemBO;

public class MainExiste {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();
        if (teste.existe(4)) {
            System.out.println("Existe");
        }
    }
}
