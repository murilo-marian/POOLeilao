package com.classes.DTO;

public class Item {
    private int itemId;
    private String itemNome;
    private double itemValor;
    private int itemDono;

    public Item(String itemNome, double itemValor, int itemDono) {
        this.itemNome = itemNome;
        this.itemValor = itemValor;
        this.itemDono = itemDono;
    }

    public Item(String itemNome) {
        this.itemNome = itemNome;
    }

    public Item() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemNome() {
        return itemNome;
    }

    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }

    public double getItemValor() {
        return itemValor;
    }

    public void setItemValor(double itemValor) {
        this.itemValor = itemValor;
    }

    public int getItemDono() {
        return itemDono;
    }

    public void setItemDono(int itemDono) {
        this.itemDono = itemDono;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("itemId=").append(itemId);
        sb.append(", itemNome='").append(itemNome).append('\'');
        sb.append(", itemValor=").append(itemValor);
        sb.append(", itemDono=").append(itemDono);
        sb.append('}');
        return sb.toString();
    }
}
