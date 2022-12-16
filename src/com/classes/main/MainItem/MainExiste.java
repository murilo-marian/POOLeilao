package com.classes.main.MainItem;

import com.classes.BO.ItemBO;

public class MainExiste {
    public static void main(String[] args) {
        ItemBO teste = new ItemBO();
        if (teste.existe(3)) {
            System.out.println("Existe");
        }
    }
}
