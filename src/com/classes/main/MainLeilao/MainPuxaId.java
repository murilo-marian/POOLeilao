package com.classes.main.MainLeilao;

import com.classes.BO.LeilaoBO;

public class MainPuxaId {
    public static void main(String[] args) {
        LeilaoBO leilaoBO = new LeilaoBO();
        System.out.println(leilaoBO.puxaId(14384));
    }
}
