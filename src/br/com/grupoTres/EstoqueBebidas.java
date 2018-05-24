package br.com.grupoTres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @authors LUCAS, BENETTI, THALLYS
 */
public class EstoqueBebidas {

    private static Inventario inventario;
    private static ArrayList<Movimentacao> movimentacoes;

    public static void main(String[] args) {
        inventario = new Inventario();
        movimentacoes = new ArrayList<>();
        boolean doFinish = false;
        Scanner scan = new Scanner(System.in);
        while (!doFinish) {
            clearConsole();
            System.out.println("");
            System.out.println("*----- Bem vindo ao controle de estoque -----*");
            System.out.println("1- Cadastrar produto");
            System.out.println("2- Registrar entrada");
            System.out.println("3- Registrar saída");
            System.out.println("4- Relatório");
            System.out.println("5- Histórico de Movimentações");
            System.out.println("0- Sair");
            System.out.println("*--------------------------------------------*");
            System.out.print("Opção: ");
            String cDigitado = scan.next();

            switch (cDigitado) {
                case "1":

                    String descricao = "";
                    while (descricao.equals("")) {
                        System.out.println("*----------------~ Cadastro ~----------------*");
                        System.out.print("Digite o nome do produto: ");
                        descricao = scan.next();
                    }
                    System.out.println("~~ PRODUTO CADASTRADO COM SUCESSO! ~~");
                    Produto produto = new Produto(descricao);
                    inventario.addProduto(produto);
                    break;

                case "2":
                    Movimentacao movimentacaoCompra = new Movimentacao();
                    movimentacaoCompra.doCompra(inventario, movimentacoes);
                    solvePrecoMedio();
                    break;

                case "3":
                    Movimentacao movimentacaoVenda = new Movimentacao();
                    movimentacaoVenda.doVenda(inventario, movimentacoes);
                    break;

                case "4":
                    System.out.println("*----------------~ Relatório ~----------------*");
                    inventario.getRelario();
                    break;

                case "5":
                    double theVeryTotal = 0;
                    for (int i = 0; i < movimentacoes.size(); i++) {
                        if (movimentacoes.get(i).getTipo().equals("entrada")) {
                            theVeryTotal -= movimentacoes.get(i).getTotal();
                        } else {
                            theVeryTotal += movimentacoes.get(i).getTotal();
                        }
                        System.out.println("Movimentação: " + i);
                        movimentacoes.get(i).showItensMovimentacao(inventario);
                        System.out.println("*----------------~       ~----------------*");
                        
                    }
                    System.out.println("Total: $" + theVeryTotal);
                    break;
                case "0":
                    System.out.println("*----------------~ Encerrando ~----------------*");
                    clearConsole();
                    doFinish = true;
                    break;
                default:
                    System.out.println("Inválido!");
                    break;
            }
        }

    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final IOException e) {
            //  Handle any exceptions.
        }
    }

    public void addProduto(Produto produto) {
        this.inventario.addProduto(produto);
    }

    public static Produto getProduto(int index) {
        return EstoqueBebidas.inventario.getProduto(index);
    }

    private static void solvePrecoMedio() {
        for (int i = 0; i < inventario.getListaProdutos().size(); i++) {
            double total = 0;
            int contador = 0;
            double media = 0;
            for (int y = 0; y < movimentacoes.size(); y++) {
                for (int x = 0; x < movimentacoes.get(y).getItensMovimentacao().size(); x++) {
                    if (movimentacoes.get(y).getItensMovimentacao().get(x).getProdutoIndex() == i) {
                        if (movimentacoes.get(y).getTipo().equals("entrada")) {
                            total += movimentacoes.get(y).getItensMovimentacao().get(x).getValorUnitario();
                            contador += 1;
                        }
                    }
                }
            }
            media = total / contador;
            inventario.setMedia(i, media);
        }
    }
}
