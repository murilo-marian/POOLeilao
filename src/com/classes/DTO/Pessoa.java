package com.classes.DTO;

import java.sql.Date;
import java.util.Arrays;

public abstract class Pessoa {
    private int id;
    private String nome;
    private long cpf;
    private Date dataNascimento;
    private String senha;
    private byte[] salt;

    public boolean isEmpty() {
        if (this.getId() == 0) {
            return true;
        }
        return false;
    }

    public Pessoa(String nome, long cpf, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(long cpf) {
        this.cpf = cpf;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", cpf=").append(cpf);
        sb.append(", dataNascimento=").append(dataNascimento);
        sb.append(", senha='").append(senha).append('\'');
        sb.append(", salt=").append(Arrays.toString(salt));
        sb.append('}');
        return sb.toString();
    }
}
