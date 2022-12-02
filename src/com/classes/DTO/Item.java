package com.classes.DTO;

public class Item {
    private int itemId;
    private String itemNome;
    private double itemValor;

    public Item(String itemNome, double itemValor) {
        this.itemNome = itemNome;
        this.itemValor = itemValor;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("itemId=").append(itemId);
        sb.append(", itemNome='").append(itemNome).append('\'');
        sb.append(", itemValor=").append(itemValor);
        sb.append('}');
        return sb.toString();
    }
}
