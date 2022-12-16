package com.classes.main.MainLeiloeiro;

import com.classes.BO.LeiloeiroBO;
import com.classes.DTO.Leiloeiro;
import com.classes.Util.Util;

import java.sql.Date;
import java.util.Scanner;

public class MainInserir {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Registro de Leiloeiro");
        Leiloeiro novo = new Leiloeiro();

        System.out.print("Nome: ");
        novo.setNome(entrada.nextLine());

        long cpf = entrada.nextLong();
        novo.setCpf(cpf);

        System.out.print("Data de Nascimento: ");
        novo.setDataNascimento(Date.valueOf(entrada.nextLine()));

        novo.setSalt(Util.getSalt());

        System.out.print("Senha: ");
        novo.setSenha(Util.criptografaSenha(entrada.nextLine(), novo.getSalt()));
        System.out.println("--------------------------");

        if (novo.registrar(novo)) {
            System.out.println("Registrado com sucesso");
        } else {
            System.out.println("Problema no registro, tente novamente");
        }

        entrada.close();
    }
}
