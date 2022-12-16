package com.classes.DAO;

import com.classes.Conexao.Conexao;
import com.classes.DTO.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    final String NOMEDATABELA = "item";

    public boolean inserir(Item item) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (itemNome, itemValor, itemDono) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getItemNome());
            ps.setDouble(2, item.getItemValor());
            ps.setInt(3, item.getItemDono());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Item item, Item novoItem) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET itemNome = ?, itemValor = ? WHERE itemID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, novoItem.getItemNome());
            ps.setDouble(2, novoItem.getItemValor());
            ps.setDouble(3, item.getItemId());
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
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE itemId = ?;";
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

    public Item procurarPorId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE itemId = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Item obj = new Item();
                obj.setItemId(rs.getInt(1));
                obj.setItemNome(rs.getString(2));
                obj.setItemValor(rs.getDouble(3));
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

    public boolean existe(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE itemId = ?;";
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

    public List<Item> procurarPorNome(String nome) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE itemNome = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Item> resultadoPesquisa = montarLista(rs);
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

    public List<Item> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " ORDER BY itemDono;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Item> resultadoPesquisa = montarLista(rs);
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

    public List<Item> pesquisarDono(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE itemDono = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                List<Item> resultadoPesquisa = montarLista(rs);
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

    public List<Item> montarLista(ResultSet rs) {
        List<Item> listObj = new ArrayList<Item>();
        try {
            do {
                Item obj = new Item();
                obj.setItemId(rs.getInt(1));
                obj.setItemNome(rs.getString(2));
                obj.setItemValor(rs.getDouble(3));
                obj.setItemDono(rs.getInt(4));
                listObj.add(obj);
            } while (rs.next());
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
