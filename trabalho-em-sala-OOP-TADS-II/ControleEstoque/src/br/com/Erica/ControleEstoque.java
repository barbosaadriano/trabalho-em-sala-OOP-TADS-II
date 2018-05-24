package br.com.Erica;

import javax.swing.JOptionPane;

public class ControleEstoque {
    
    public static void main(String[] args) {
        ListaProduto lp = new ListaProduto();
        
        OUTER:
        while (lp.isTerminou() == false) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "O que deseja fazer?\r\n"
                    + "1 para Cadastrar produto\r\n"
                    + "2 para Compra de produto\r\n"
                    + "3 para Venda de produto\r\n"
                    + "4 para Inventário\r\n"
                    + "5 para Relatório\r\n"
                    + "6 para Finalizar compra\r\n"
                    + "7 para Listar Produtos\r\n"
                    + "0 para Sair s/ finalizar"));
            switch (opcao) {
                case 1:
                    lp.cadastrar();
                    break;
                case 2:
                    lp.compra();
                    break;
                case 3:
                    lp.venda();
                    break;
                case 4:
                    lp.listarCarrinho();
                    break;
                case 5:
                    lp.relatorio();
                    break;
                case 6:
                    lp.finalizar();
                    break;
                case 7:
                    lp.listarProdutos();
                    break;
                case 0:
                    break OUTER;
                default:
                    break;
            }
            if (lp.getContador() == 100) {
                lp.finalizar();
            }
        }

    }

}
