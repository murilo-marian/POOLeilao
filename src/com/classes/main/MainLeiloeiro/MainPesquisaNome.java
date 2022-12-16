package com.classes.main.MainLeiloeiro;

import com.classes.BO.LeiloeiroBO;

public class MainPesquisaNome {
    public static void main(String[] args) {
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        System.out.println(leiloeiroBO.pesquisaNome("m"));
    }
}
