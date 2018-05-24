package br.com.Erica;

import javax.swing.JOptionPane;

public class ListaProduto {

    private boolean terminou;

    private CadastroProduto[] cp;
    private int contCP;

    private String[] relatorio;
    private String[] produto;
    private float[] valor;
    private float valorTotal;
    private int contador;

    private String[] iProduto;
    private int[] iQuantidade;
    private float[] iValor;
    private int iCont;

    public ListaProduto() {
        terminou = false;
        relatorio = new String[100];
        produto = new String[100];
        valor = new float[100];
        valorTotal = 0;
        contador = 0;
        cp = new CadastroProduto[5];
        contCP = 0;
        iProduto = new String[100];
        iQuantidade = new int[100];
        iValor = new float[100];
        iCont = 0;
    }

    public void compra() {
        this.listarProdutos();
        int cod = this.isProdutoCadastrado();

        int nf = cp[cod].getNotaFiscal();
        String produtoNome = cp[cod].getNomeProduto();
        int qte = Integer.parseInt(JOptionPane.showInputDialog("Informe a Quantidade"));
        float valorUnitario = cp[cod].getValorProduto();
        int estoque = cp[cod].getQteProduto();

        cp[cod].setQteProduto(estoque + qte);
        System.out.println(estoque);
        this.relatorioProduto(nf, produtoNome, qte, valorUnitario, 1);
    }

    public int isProdutoCadastrado() {
        String tmp = null;
        int cod = 0;
        while (tmp == null) {
            tmp = JOptionPane.showInputDialog("Informe o ID Produto");
            try {
                cod = Integer.parseInt(tmp);
                if ((cod < 0) || (cod > this.contCP - 1)) {
                    throw new NullPointerException("Produto não listado");
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                tmp = null;
            }
        }
        return cod;
    }

    public void venda() {
        this.listarProdutos();
        int cod = this.isProdutoCadastrado();

        int nf = cp[cod].getNotaFiscal();
        String produtoNome = cp[cod].getNomeProduto();
        int qte = Integer.parseInt(JOptionPane.showInputDialog("Informe a Quantidade"));
        float valorUnitario = cp[cod].getValorProduto();
        int estoque = cp[cod].getQteProduto();

        if (cp[cod].verificaQteProduto(qte)) {
            cp[cod].setQteProduto(estoque - qte);
            this.relatorioProduto(nf, produtoNome, qte, valorUnitario, 2);
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade informada não possui no estoque");
        }
    }

    public void relatorioProduto(int nf, String produto, int quantidade, float valor, int tipo) {
        if (tipo == 1) {
            this.relatorio[this.contador] = "Entrada do produto: " + produto
                    + " Quantidade: " + quantidade + " NF: " + nf
                    + " Valor: " + (valor * quantidade);
            this.valor[this.contador] = (valor * quantidade) * -1;
        } else {
            this.relatorio[this.contador] = "Saída do produto: " + produto
                    + " Quantidade: " + quantidade + " NF: " + nf
                    + " Valor: " + ((valor * quantidade) * 1.5f);
            this.valor[this.contador] = (valor * quantidade) * 1.5f;
            
            this.iProduto[this.iCont] = produto;
            this.iQuantidade[this.iCont] = quantidade;
            this.iValor[this.iCont] = (valor * quantidade) * 1.5f;
            this.iCont++;
        }
        this.produto[this.contador] = produto;
        this.contador++;
    }

    public int getContador() {
        return contador;
    }

    public void calcularValorTotal() {
        for (int i = 0; i < this.contador; i++) {
            this.valorTotal = this.valorTotal + this.valor[i];
        }
    }

    public void listarCarrinho() {
        float precoMedio = 0;
        for (int i = 0; i < this.iCont; i++) {
            System.out.printf("\r\nProduto: %s\tQuantidade: %d\tValor: %.2f", this.iProduto[i], this.iQuantidade[i], this.iValor[i]);
            precoMedio = precoMedio + this.iValor[i];
        }
        System.out.println("\r\nPreço Médio: " + precoMedio / this.iCont);
        System.out.println("Valor Total: " + precoMedio);
    }

    public void relatorio() {
        if (this.contador > 0) {
            float precoMedio = 0;
            for (int i = 0; i < this.contador; i++) {
                System.out.println(relatorio[i]);
                precoMedio = precoMedio + this.valor[i];
            }
            System.out.println("Preço Médio: " + precoMedio / this.contador);
            System.out.println("Valor Total: " + precoMedio);
        }
    }

    public void finalizar() {
        if (this.iCont > 0) {
            System.out.println("");
            this.relatorio();
        }
        System.out.println("Programa finalizado.");
        this.setTerminou(true);
    }

    public void cadastrar() {
        String nomeProduto = JOptionPane.showInputDialog("Nome do Produto");
        float valorProduto = Float.parseFloat(JOptionPane.showInputDialog("Valor do Produto"));
        int qteProduto = Integer.parseInt(JOptionPane.showInputDialog("Quantidade"));
        int nf = Integer.parseInt(JOptionPane.showInputDialog("Informe o NF"));

        this.cp[contCP] = new CadastroProduto(nomeProduto, valorProduto, qteProduto, nf);
        contCP++;
    }

    public void listarProdutos() {
        for (int i = 0; i < contCP; i++) {
            System.out.printf("\r\nID: %d\tProduto: %s\tValor: %.2f\tQuantidade: %d\tNota Fiscal: %d",
                    i, cp[i].getNomeProduto(), cp[i].getValorProduto(), cp[i].getQteProduto(), cp[i].getNotaFiscal());
        }
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }
}
