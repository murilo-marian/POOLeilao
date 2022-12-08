package com.classes.DAO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classes.DTO.Pessoa;
import com.classes.Conexao.Conexao;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PessoaDAO {

    final String NOMEDATABELA = "cliente";

    public boolean inserir(Pessoa pessoa, String senha) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (idCliente, nome, cpf, nascimento, senha, salt) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pessoa.getId());
            ps.setString(2, pessoa.getNome());
            ps.setLong(3, pessoa.getCpf());
            ps.setDate(4, pessoa.getDataNascimento());
            byte[] salt = getSalt();
            ps.setString(5, criptografaSenha(senha, salt));
            ps.setBytes(6, salt);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Pessoa pessoa, Pessoa alterada) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, cpf = ?, nascimento = ? WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alterada.getNome());
            ps.setLong(2, alterada.getCpf());
            ps.setDate(3, alterada.getDataNascimento());
            ps.setInt(4, pessoa.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pessoa procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pessoa obj = new Pessoa();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setCpf(rs.getLong(3));
                obj.setDataNascimento(rs.getDate(4));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pessoa procuraCpf(long cpf) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE cpf = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pessoa obj = new Pessoa();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setCpf(rs.getLong(3));
                obj.setDataNascimento(rs.getDate(4));
                obj.setSenha(rs.getString(5));
                obj.setSalt(rs.getBytes(6));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Pessoa> procurarPorNome(String nome) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Pessoa> resultadoPesquisa = montarLista(rs);
                ps.close();
                rs.close();
                conn.close();
                return resultadoPesquisa;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<Pessoa> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Pessoa> resultadoPesquisa = montarLista(rs);
                ps.close();
                rs.close();
                conn.close();
                return resultadoPesquisa;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String criptografaSenha(String senha, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iteracoes = 1000;
        char[] chars = senha.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iteracoes, 64*8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();

        return toHex(hash);
    }

    public byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    public boolean testaSenha(long cpf, String senha) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Pessoa pessoa = procuraCpf(cpf);
        if (pessoa.getSenha().equals(criptografaSenha(senha, pessoa.getSalt()))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existeCpf(long cpf) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE cpf = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean existe(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Pessoa> montarLista(ResultSet rs) {
        List<Pessoa> listObj = new ArrayList<Pessoa>();
        try {
            do {
                Pessoa obj = new Pessoa();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setCpf(rs.getLong(3));
                obj.setDataNascimento(rs.getDate(4));
                listObj.add(obj);
            } while (rs.next());
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}