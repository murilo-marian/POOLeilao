package com.classes.DTO;

import com.classes.BO.LeiloeiroBO;
import com.classes.ILogin;

public class Leiloeiro extends Pessoa implements ILogin {

    public boolean logar(long cpf, String senha) {
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        if (leiloeiroBO.testaSenha(cpf, senha)) {
            return true;
        }
        return false;
    }

    public boolean registrar(Pessoa novo) {
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        return leiloeiroBO.inserir(novo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Leiloeiro{");
        sb.append(' ').append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
