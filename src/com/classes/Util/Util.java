package com.classes.Util;

import com.classes.DTO.ClienteFake;
import com.classes.DTO.Item;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.Scanner;

public class Util {


    //TESTE PARA SABER SE O CPF É VÁLIDO
    public static boolean checaCPF(long cpf) {
        int numDigits = String.valueOf(cpf).length();
        if (numDigits != 11) {
            return false;
        }
        int[] cpfSeparado = cpfPraArray(cpf);
        int somaUm = 0;
        for (int i = 1, j = 10; i < 10; i++, j--) {
            somaUm += j * cpfSeparado[i];
        }
        somaUm = 11 - (somaUm % 11);

        int somaDois = 0;
        for (int i = 1, j = 11; i < 11; i++, j--) {
            somaDois += j * cpfSeparado[i];
        }
        somaDois = 11 - (somaDois % 11);
        if (((somaUm == cpfSeparado[10]) || (somaUm > 10 && cpfSeparado[10] == 0)) && ((somaDois == cpfSeparado[11]) || (somaDois > 10 && cpfSeparado[11] == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] cpfPraArray(long cpf) {
        String cpfString = Long.toString(cpf);
        int[] cpfArray = new int[12];
        for (int i = 11; i > 0; i--) {
            cpfArray[i] = Integer.parseInt(String.valueOf(cpfString.charAt(i - 1)));
        }
        return cpfArray;
    }

    //Hash
    public static String criptografaSenha(String senha, byte[] salt) {
        try {
            int iteracoes = 1000;
            char[] chars = senha.toCharArray();
            PBEKeySpec spec = new PBEKeySpec(chars, salt, iteracoes, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = skf.generateSecret(spec).getEncoded();

            return toHex(hash);

        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        }
    }

    public static byte[] getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        } catch (Exception e) {
            System.out.println("teste");
            return null;
        }
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    //geração de item
    public static Item geraItem() {
        Item gerado = new Item();
        gerado.setItemNome(geraItemNome());
        gerado.setItemValor(geraItemValor());
        return gerado;
    }

    private static String geraItemNome() {
        Random random = new Random();

        String itensPath = new File("src/TextResources/Itens/Itens.txt").getAbsolutePath();
        int linhasItens = contaLinhas(itensPath);

        String materiaisPath = new File("src/TextResources/Itens/Materiais.txt").getAbsolutePath();
        int linhasMateriais = contaLinhas(materiaisPath);

        String historicoPath = new File("src/TextResources/Itens/Historico.txt").getAbsolutePath();
        int linhasHistorico = contaLinhas(historicoPath);

        String objeto;
        try {
            objeto = Files.readAllLines(Paths.get(itensPath)).get(random.nextInt(linhasItens));
            objeto += " de " + Files.readAllLines(Paths.get(materiaisPath)).get(random.nextInt(linhasMateriais));
            objeto += " de " + Files.readAllLines(Paths.get(historicoPath)).get(random.nextInt(linhasHistorico));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return objeto;
    }

    private static int contaLinhas(String path) {
        int linhas = 0;

        try {
            BufferedReader readerItens = new BufferedReader(new FileReader(path));

            while (readerItens.readLine() != null) linhas++;
            readerItens.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return linhas;
    }

    private static double geraItemValor() {
        Random gera = new Random();
        int valor = gera.nextInt(1, 10);
        valor *= 10000;
        return valor;
    }

    //Gerador de nomes

    //geração de item
    public static ClienteFake geraClienteFake(double valor) {
        ClienteFake gerado = new ClienteFake();
        gerado.setNome(geraFakeNome());
        gerado.aleatorizaLimite(valor);
        return gerado;
    }

    private static String geraFakeNome() {
        Random random = new Random();

        String itensPath = new File("src/TextResources/Nomes/NomesGenericos.txt").getAbsolutePath();
        int linhasNomes = contaLinhas(itensPath);

        String sobrenomesPath = new File("src/TextResources/Nomes/Sobrenomes.txt").getAbsolutePath();
        int linhasMateriais = contaLinhas(sobrenomesPath);

        String objeto;
        try {
            objeto = Files.readAllLines(Paths.get(itensPath)).get(random.nextInt(linhasNomes));
            objeto += " " + Files.readAllLines(Paths.get(sobrenomesPath)).get(random.nextInt(linhasMateriais));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return objeto;
    }
}
