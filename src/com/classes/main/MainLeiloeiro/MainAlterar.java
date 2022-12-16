package com.classes.main.MainLeiloeiro;

import com.classes.BO.ClienteBO;
import com.classes.BO.LeiloeiroBO;
import com.classes.DTO.Cliente;
import com.classes.DTO.Leiloeiro;
import com.classes.DTO.Pessoa;

public class MainAlterar {
    public static void main(String[] args) {

        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();

        Leiloeiro leiloeiro = leiloeiroBO.pesquisaCPF(11111111111l);
        Leiloeiro velho = leiloeiroBO.procurarPorId(2);
        leiloeiroBO.alterar(velho, leiloeiro);
        System.out.println(leiloeiroBO.procurarPorId(4));
    }
}
