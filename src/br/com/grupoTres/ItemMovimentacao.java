package br.com.grupoTres;

import java.util.Date;

public class ItemMovimentacao {

    private int produtoIndex;
    private int quantidade;
    private double valorUnitario;
    private final Date dateTime;

    public Date getDateTime() {
        return dateTime;
    }

    
    
    public ItemMovimentacao(int produtoIndex, int quantidade, double valorUnitario) {
        this.produtoIndex = produtoIndex;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        Date date = new Date();
        this.dateTime = date;
    }

    public int getProdutoIndex() {
        return this.produtoIndex;
    }

    public void setProduto(int produtoIndex) {
        this.produtoIndex = produtoIndex;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return this.valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
