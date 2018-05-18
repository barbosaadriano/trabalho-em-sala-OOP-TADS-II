package br.com.grupoTres;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Movimentacao {

    private String NF;
    private String tipo;
    private ArrayList<ItemMovimentacao> itensMovimentacao;
    private double total;

    public void setNF(String NF) {
        this.NF = NF;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Movimentacao(String NF, String tipo) {
        this.NF = NF;
        this.tipo = tipo;
        itensMovimentacao = new ArrayList<>();
    }

    public Movimentacao() {
        itensMovimentacao = new ArrayList<>();
    }

    public String getNF() {
        return NF;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<ItemMovimentacao> getItensMovimentacao() {
        return itensMovimentacao;
    }

    public void addItemCompra(int index, int quantidade, double valorUnitario) {
        if (this.tipo.equals("entrada")) {
            ItemMovimentacao item = new ItemMovimentacao(index, quantidade, valorUnitario);
            this.itensMovimentacao.add(item);
            this.total += valorUnitario * quantidade;
        }

    }

    public void addItemVenda(int index, int quantidade) {
        if (this.tipo.equals("saida")) {

            double valor = EstoqueBebidas.getProduto(index).getPrecoMedio() * 1.5;
            ItemMovimentacao item = new ItemMovimentacao(index, quantidade, valor);
            itensMovimentacao.add(item);
            this.total += valor * quantidade;
        }
    }

    public void doCompra(Inventario inventario, ArrayList<Movimentacao> movimentacoes) {
        Scanner scan = new Scanner(System.in);
        setTipo("entrada");
        if (inventario.getListaProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
        }
        System.out.println("*----------------~ Compra ~----------------*");
        inventario.getRelario();
        boolean finalizar = false;
        String NF_ = "";
        while (NF_.equals("")) {
            System.out.println("Digite a NF:");
            NF_ = scan.next();
        }
        setNF(NF_);
        Movimentacao myMovimentation = new Movimentacao(NF_, "entrada");
        while (!finalizar) {
            Integer index = -15;

            while (index < 0 || index > inventario.getListaProdutos().size() - 1) {
                index = -15;
                System.out.print("Digite o index do produto: ");
                index = scan.nextInt();
            }
            Integer qtd = -15;
            while (qtd < 0) {
                qtd = -15;
                System.out.print("Digite a quantidade do produto: ");
                qtd = scan.nextInt();
            }

            double valor = -15;
            while (valor < 0) {
                valor = -15;
                System.out.print("Digite o valor do produto: ");
                valor = scan.nextFloat();
            }

            myMovimentation.addItemCompra(index, qtd, valor);
            //
            //CONTINUAR AQUI
            System.out.println("1- Adicionar mais produtos\n"
                    + "Outra tecla - Finalizar");
            System.out.print("Opção: ");
            String whatToDo = scan.next();
            if (!whatToDo.equals("1")) {
                finalizar = true;
            }

        }
        movimentacoes.add(myMovimentation);
        //Mudar a qtd de produtos
        for (int i = 0; i < movimentacoes.get(movimentacoes.size() - 1).getItensMovimentacao().size(); i++) {
            int qtd = movimentacoes.get(movimentacoes.size() - 1).getItensMovimentacao().get(i).getQuantidade();
            int index = movimentacoes.get(movimentacoes.size() - 1).getItensMovimentacao().get(i).getProdutoIndex();

            inventario.handleQuantidade(index, qtd, "entrada");
        }

        System.out.println("Total: " + myMovimentation.getTotal());

    }

    public void doVenda(Inventario inventario, ArrayList<Movimentacao> movimentacoes) {
        Scanner scan = new Scanner(System.in);
        setTipo("saida");
        if (inventario.getListaProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
        }
        System.out.println("*----------------~ Venda ~----------------*");
        inventario.getRelario();
        boolean finalizar_ = false;
        String NF_ = "";
        while (NF_.equals("")) {
            System.out.println("Digite a NF:");
            NF_ = scan.next();
        }
        setNF(NF_);
        Movimentacao myMovimentation_ = new Movimentacao(NF_, "saida");
        while (!finalizar_) {
            Integer index = -15;

            while (index < 0 || index > inventario.getListaProdutos().size() - 1) {
                index = -15;
                System.out.print("Digite o index do produto: ");
                index = scan.nextInt();
            }
            Integer qtd = -15;
            while (qtd < 0) {
                qtd = -15;
                System.out.print("Digite a quantidade do produto: ");
                qtd = scan.nextInt();
            }
            myMovimentation_.addItemVenda(index, qtd);
            System.out.println("1- Adicionar mais produtos\n"
                    + "Outra tecla - Finalizar");
            System.out.print("Opção: ");
            String whatToDo = scan.next();
            if (!whatToDo.equals("1")) {
                finalizar_ = true;
            }

        }
        movimentacoes.add(myMovimentation_);
        for (int i = 0; i < movimentacoes.get(movimentacoes.size() - 1).getItensMovimentacao().size(); i++) {
            int qtd = movimentacoes.get(movimentacoes.size() - 1).getItensMovimentacao().get(i).getQuantidade();
            int index = movimentacoes.get(movimentacoes.size() - 1).getItensMovimentacao().get(i).getProdutoIndex();

            inventario.handleQuantidade(index, qtd, "saida");
        }
        System.out.println("Total: " + myMovimentation_.getTotal());
    }

    public double getTotal() {
        return this.total;
    }

    public void showItensMovimentacao(Inventario inventario) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dStr;
        String aux;
        if (this.tipo.equals("entrada")) {
            aux = "-";
        } else {
            aux = "+";
        }
        for (int i = 0; i < itensMovimentacao.size(); i++) {
            dStr = dateFormat.format(itensMovimentacao.get(i).getDateTime());
            String nome = inventario.getListaProdutos().get(itensMovimentacao.get(i).getProdutoIndex()).getDescricao();
            int qtd = itensMovimentacao.get(i).getQuantidade();
            double vlrUnt = itensMovimentacao.get(i).getValorUnitario();
            double totalItem = qtd * vlrUnt;
            System.out.println(dStr + " - " + nome + "\t\t ~ " + qtd + "un x " + aux + "$" + vlrUnt + " (" + aux + "$" + totalItem + ")");
        }

    }

}
