package com.classes.DTO;

public class Leilao {
    private int idLeilao;
    private int idTransacao;
    private int idUsuario;
    private double valorLance;

    public Leilao(int idLeilao, int idTransacao, int idUsuario, double valorLance) {
        this.idLeilao = idLeilao;
        this.idTransacao = idTransacao;
        this.idUsuario = idUsuario;
        this.valorLance = valorLance;
    }

    public Leilao() {
    }

    public int getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(int idLeilao) {
        this.idLeilao = idLeilao;
    }


    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getValorLance() {
        return valorLance;
    }

    public void setValorLance(double valorLance) {
        this.valorLance = valorLance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Leilao{");
        sb.append("idLeilao=").append(idLeilao);
        sb.append(", idTransacao=").append(idTransacao);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", valorLance=").append(valorLance);
        sb.append('}');
        return sb.toString();
    }

}
