package br.com.grupoTres;


public class Produto {
    private String descricao;
    private int saldo;
    private double precoMedio;
    
    public Produto(String descricao) {
        this.descricao = descricao;
        this.saldo = 0;
        this.precoMedio = 0;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }
}
