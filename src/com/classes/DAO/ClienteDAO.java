package com.classes.DAO;

import com.classes.BO.ClienteBO;
import com.classes.Conexao.Conexao;
import com.classes.DTO.Cliente;
import com.classes.DTO.Pessoa;
import com.classes.Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    final String NOMEDATABELA = "cliente";

    //CREATE
    public boolean inserir(Pessoa cliente) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (nome, cpf, nascimento, senha, salt) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setLong(2, cliente.getCpf());
            ps.setDate(3, cliente.getDataNascimento());
            ps.setString(4, cliente.getSenha());
            ps.setBytes(5, cliente.getSalt());
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
    public boolean alterar(Cliente atual, Cliente alterada) {
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

    public boolean atualizarFundos(double fundos, int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET orcamento = ? WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, fundos);
            ps.setDouble(2, id);
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

    //READ
    public Cliente pesquisarID(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente obj = new Cliente();
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

    public Cliente pesquisarCPF(long cpf) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE cpf = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setCpf(rs.getLong(3));
                obj.setDataNascimento(rs.getDate(4));
                obj.setSenha(rs.getString(5));
                obj.setSalt(rs.getBytes(6));
                obj.setOrcamento(rs.getDouble(7));
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

    public List<Cliente> pesquisarNome(String nome) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Cliente> resultadoPesquisa = montarLista(rs);
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

    public List<Cliente> pesquisaTudo() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Cliente> resultadoPesquisa = montarLista(rs);
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

    public List<Cliente> montarLista(ResultSet rs) {
        List<Cliente> listObj = new ArrayList<Cliente>();
        try {
            do {
                Cliente obj = new Cliente();
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

    public double puxaOrcamento(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT orcamento FROM " + NOMEDATABELA + " WHERE idCliente = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double orcamento = rs.getDouble(1);
                ps.close();
                rs.close();
                conn.close();
                return orcamento;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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


    //LOGIN
    public boolean testaSenha(long cpf, String senha) {
        ClienteBO clienteBO = new ClienteBO();
        Util util = new Util();
        if (clienteBO.pesquisaCPF(cpf).getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }
}
