package com.classes.main.MainLeiloeiro;

import com.classes.BO.LeiloeiroBO;

public class MainProcurarID {
    public static void main(String[] args) {
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        System.out.println(leiloeiroBO.procurarPorId(1));
    }
}
