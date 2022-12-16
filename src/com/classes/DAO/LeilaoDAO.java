package com.classes.DAO;

import com.classes.Conexao.Conexao;
import com.classes.DTO.Cliente;
import com.classes.DTO.ClienteFake;
import com.classes.DTO.Item;
import com.classes.DTO.Leilao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LeilaoDAO {
    final String NOMEDATABELA = "leiloes";

    public boolean inserir(List<BigDecimal> lances, List<Object> lancadores) {
        try {
            Cliente cliente = new Cliente();
            int i = 0;
            Random geraid = new Random();
            int idLeilao = geraid.nextInt(3000, 15000);
            for (Object lanca : lancadores) {
                i += 1;
                int idUsuario = 0;
                if (lanca instanceof ClienteFake) {
                    idUsuario = geraid.nextInt(3000, 15000);
                } else if (lanca instanceof Cliente) {
                    idUsuario = ((Cliente) lanca).getId();
                }
                Connection conn = Conexao.conectar();
                String sql = "INSERT INTO " + NOMEDATABELA + " (idLeilao, idTransacao, idUsuario, valorLance) VALUES (?, ?, ?, ?);";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, idLeilao);
                ps.setInt(2, i);
                ps.setInt(3, idUsuario);
                ps.setDouble(4, lances.get(i - 1).doubleValue());
                ps.executeUpdate();
                ps.close();
                conn.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //READ
    public List<Leilao> puxaId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idLeilao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Leilao> resultadoPesquisa = montarLista(rs);
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

    public List<Leilao> montarLista (ResultSet rs){
        List<Leilao> listObj = new ArrayList<Leilao>();
        try {
            do {
                Leilao obj = new Leilao();
                obj.setIdLeilao(rs.getInt(1));
                obj.setIdTransacao(rs.getInt(2));
                obj.setIdUsuario(rs.getInt(3));
                obj.setValorLance(rs.getDouble(4));
                listObj.add(obj);
            } while (rs.next());
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean alterar(Leilao novo, Leilao velho) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET idUsuario = ?, valorLance = ? WHERE idLeilao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, novo.getIdUsuario());
            ps.setDouble(2, novo.getValorLance());
            ps.setDouble(3, velho.getIdLeilao());
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
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE idLeilao = ?;";
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
}
