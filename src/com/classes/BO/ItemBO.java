package com.classes.BO;
import com.classes.DAO.ItemDAO;
import com.classes.DTO.Item;

import java.util.List;

public class ItemBO {
    public boolean inserir(Item item) {
                ItemDAO pessoaDAO = new ItemDAO();
                return pessoaDAO.inserir(item);
    }

    public boolean alterar(Item item, Item alterada) {
        ItemDAO pessoaDao = new ItemDAO();
        return pessoaDao.alterar(item, alterada);
    }

    public boolean excluir(int id) {
        if (existe(id)) {
            ItemDAO pessoaDAO = new ItemDAO();
            return pessoaDAO.excluir(id);
        }
        return false;
    }

    public Item procurarPorId(int id) {
        ItemDAO pessoaDAO = new ItemDAO();
        return pessoaDAO.procurarPorId(id);
    }

    public List<Item> procurarPorNome(String nome) {
        ItemDAO pessoaDAO = new ItemDAO();
        return pessoaDAO.procurarPorNome(nome);
    }

    public List<Item> pesquisarTudo() {
        ItemDAO pessoaDAO = new ItemDAO();
        return  pessoaDAO.pesquisarTodos();
    }

    public boolean existe(int id) {
        ItemDAO pessoaDAO = new ItemDAO();
        return pessoaDAO.existe(id);
    }
}
