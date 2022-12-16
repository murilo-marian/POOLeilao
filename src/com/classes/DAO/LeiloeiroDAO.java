package com.classes.DAO;

import com.classes.BO.ClienteBO;
import com.classes.BO.LeiloeiroBO;
import com.classes.Conexao.Conexao;
import com.classes.DTO.Leiloeiro;
import com.classes.DTO.Pessoa;
import com.classes.Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeiloeiroDAO {

    final String NOMEDATABELA = "leiloeiro";

    //CREATE
    public boolean inserir(Pessoa leiloeiro) {
        try {
            Connection conn = Conexao.conectar();
            Util util = new Util();
            String sql = "INSERT INTO " + NOMEDATABELA + " (nome, cpf, nascimento, senha, salt) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, leiloeiro.getNome());
            ps.setLong(2, leiloeiro.getCpf());
            ps.setDate(3, leiloeiro.getDataNascimento());
            ps.setString(4, leiloeiro.getSenha());
            ps.setBytes(5, leiloeiro.getSalt());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //UPDATE
    public boolean alterar(Leiloeiro atual, Leiloeiro alterada) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, cpf = ?, nascimento = ? WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alterada.getNome());
            ps.setLong(2, alterada.getCpf());
            ps.setDate(3, alterada.getDataNascimento());
            ps.setInt(4, atual.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //DELETE
    public boolean excluir(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idLeiloeiro = ?;";
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

    //READ
    public Leiloeiro pesquisarID(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idLeiloeiro = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Leiloeiro obj = new Leiloeiro();
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

    public Leiloeiro pesquisarCPF(long cpf) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE cpf = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Leiloeiro obj = new Leiloeiro();
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

    public List<Leiloeiro> pesquisarNome(String nome) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Leiloeiro> resultadoPesquisa = montarLista(rs);
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

    public List<Leiloeiro> pesquisaTudo() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Leiloeiro> resultadoPesquisa = montarLista(rs);
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

    public List<Leiloeiro> montarLista(ResultSet rs) {
        List<Leiloeiro> listObj = new ArrayList<Leiloeiro>();
        try {
            do {
                Leiloeiro obj = new Leiloeiro();
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

    //CHECAGEM DE EXISTENCIA
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
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idLeiloeiro = ?;";
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


    //LOGIN
    public boolean testaSenha(long cpf, String senha) {
        LeiloeiroBO leiloeiroBO = new LeiloeiroBO();
        if (leiloeiroBO.pesquisaCPF(cpf).getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }
}
