package com.classes.BO;

import com.classes.DAO.LeilaoDAO;
import com.classes.DTO.Leilao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class LeilaoBO {
    public boolean inserir(List<BigDecimal> lances, List<Object> lancadores) {
        LeilaoDAO leilaoDAO = new LeilaoDAO();
        return leilaoDAO.inserir(lances, lancadores);
    }

    public List<Leilao> puxaId(int id) {
        LeilaoDAO leilaoDAO = new LeilaoDAO();
        return leilaoDAO.puxaId(id);
    }

    public boolean alterar(Leilao novo, Leilao antigo) {
        LeilaoDAO leilaoDAO = new LeilaoDAO();
        return leilaoDAO.alterar(novo, antigo);
    }

    public boolean excluir(int id) {
        LeilaoDAO leilaoDAO = new LeilaoDAO();
        return leilaoDAO.excluir(id);
    }
}
