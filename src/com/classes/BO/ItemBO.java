package com.classes.BO;
import com.classes.DAO.ItemDAO;
import com.classes.DTO.Item;

import java.util.List;

public class ItemBO {
    //Create
    public boolean inserir(Item item) {
                ItemDAO itemDAO = new ItemDAO();
                return itemDAO.inserir(item);
    }

    //UPDATE
    public boolean alterar(Item item, Item alterada) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.alterar(item, alterada);
    }

    //DELETE
    public boolean excluir(int id) {
        if (existe(id)) {
            ItemDAO itemDAO = new ItemDAO();
            return itemDAO.excluir(id);
        }
        return false;
    }

    //READ
    public Item procurarPorId(int id) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.procurarPorId(id);
    }

    public List<Item> procurarPorNome(String nome) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.procurarPorNome(nome);
    }

    public List<Item> pesquisarTudo() {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.pesquisarTodos();
    }
    
    public List<Item> pesquisarDono(int id) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.pesquisarDono(id);
    }

    //Checa existência
    public boolean existe(int id) {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.existe(id);
    }
}
