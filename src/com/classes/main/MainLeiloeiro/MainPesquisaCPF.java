package com.classes.main.MainLeiloeiro;

import com.classes.BO.LeiloeiroBO;

public class MainPesquisaCPF {
    public static void main(String[] args) {
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        System.out.println(leiloeiroBO.pesquisaCPF(11111111111l));
    }
}
