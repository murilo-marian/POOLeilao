package com.classes.DTO;

import java.util.Random;

public class ClienteFake {
    private String nome;
    private double limite;

    public void aleatorizaLimite(double itemValor) {
        Random gera = new Random();
        if (gera.nextInt(1, 10) > 2) {
            double limite = gera.nextInt(1, 25) * 5 / 100d;
            setLimite((limite*itemValor) + itemValor);
        } else {
            double limite = gera.nextInt(15, 30) * 5 / 100d;
            setLimite((limite*itemValor) + itemValor);
        }
    }

    public double aleatorizaLance(double lanceAtual) {
        Random gera = new Random();
        if (gera.nextInt(1, 20) < 18) {
            if (gera.nextInt(1, 10) > 2) {
                double lance = gera.nextInt(1, 10);
                return (lance * 1000) + ((int) lanceAtual / 100) * 100;
            } else {
                double lance = gera.nextInt(5, 7);
                return (lance * 1000) + ((int) lanceAtual / 100) * 100;
            }
        }
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClienteFake{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", limite=").append(limite);
        sb.append('}');
        return sb.toString();
    }
}
