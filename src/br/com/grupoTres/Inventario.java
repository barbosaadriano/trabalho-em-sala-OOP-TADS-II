package br.com.grupoTres;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Produto> produtos;

    public Inventario() {
        produtos = new ArrayList<>();
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public void handleQuantidade(int index, int quantidade, String tipo) {
        int atual = this.produtos.get(index).getSaldo();
        switch (tipo) {
            case "entrada":
                this.produtos.get(index).setSaldo(atual + quantidade);
                break;
            case "saida":
                this.produtos.get(index).setSaldo(atual - quantidade);
                break;
            default:
                System.out.println("ERRO 001: TIPO INCORRETO");
                break;
        }

    }

    public Produto getProduto(int index) {
        return this.produtos.get(index);
    }

    public void getRelario() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + " --> " + this.produtos.get(i).getDescricao() + "");
            System.out.println("\t --  Saldo: " + this.produtos.get(i).getSaldo());
            System.out.println("\t --  Preço Médio: " + this.produtos.get(i).getPrecoMedio());
        }
    }

    public ArrayList<Produto> getListaProdutos() {
        return this.produtos;
    }

    public void setMedia(int index, double media) {
        this.produtos.get(index).setPrecoMedio(media);
    }
}
