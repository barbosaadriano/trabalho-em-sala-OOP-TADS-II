package br.com.pedroenju;

public class CadastroProduto {

    private String nomeProduto;
    private float valorProduto;
    private int qteProduto;
    private int notaFiscal;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public CadastroProduto(String nomeProduto, float valorProduto, int qteProduto, int notaFiscal) {
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.qteProduto = qteProduto;
        this.notaFiscal = notaFiscal;
    }

    public float getValorProduto() {
        return valorProduto;
    }

    public int getQteProduto() {
        return qteProduto;
    }

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setQteProduto(int qteProduto) {
        this.qteProduto = qteProduto;
    }
    
    public boolean verificaQteProduto(int num) {
        return (this.qteProduto - num) >= 0;
    }
    
}
