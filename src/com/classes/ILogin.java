package com.classes;

import com.classes.DTO.Pessoa;

public interface ILogin {
    public boolean logar(long cpf, String senha);
    public boolean registrar(Pessoa novo);
}
