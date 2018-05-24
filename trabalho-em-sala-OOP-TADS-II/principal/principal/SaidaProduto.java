package principal;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiag
 */

public class SaidaProduto {
      public static void main ( String[] args){
        SaidaProduto saida = new SaidaProduto();
        saida.RodaAi();
      }
      private int tipo;
      private int saldoent;
      private int saldosaida;
      private int Entrada;
      private int Saida;
      private int opcao;
      private int[] numeronfee;
      private String[] nomeprodutoe;
      private int [] qtdeprodutoe;
      private float [] precounite;
      private int [] numeronfes;
      private String [] nomeprodutos;
      private int [] qtdeprodutos;
      private float [] precounits;
      private int contadore;
      private int contadors;
      
      
      
      public SaidaProduto(){
      this.nomeprodutoe = new String[100];
      this.nomeprodutos = new String[100];
      this.numeronfee = new int[100];
      this.numeronfes = new int[100];
      this.precounite = new float[100];
      this.precounits = new float[100];
      this.qtdeprodutoe = new int[100];
      this.qtdeprodutos = new int[100];
      this.contadore = 0;
      this.contadors = 0;
      
      }
      
        
      public int getEntrada() {
          return Entrada;
      }
      public void setEntrada(int entrada) {
          this.Entrada = entrada;
      }
      
      public int getSaida(){
          return Saida;
      }
 
      public void setSaida(int saida){
          Saida = saida;
      }
      public void RodaAi(){
          boolean terminou = false;
          while(!terminou){
          Scanner scan = new Scanner(System.in);
          
          System.out.println("Informe o que Deseja Fazer");
          System.out.println("1 Para Entrada");
          System.out.println("2 Para Saida");
          System.out.println("0 Para Sair");
          
          opcao = scan.nextInt();
          
          switch(opcao){
             case 1:{
             System.out.println("Informe Nº NFE");
             numeronfee[contadore]= scan.nextInt();
             System.out.println("Informe o nome do Produto");
             nomeprodutoe[contadore] = scan.next();
             System.out.println("Informe a Quantidade de Produtos");
             qtdeprodutoe[contadore] = scan.nextInt();
             System.out.println("Informe o Preço Unitário");
             precounite[contadore] = scan.nextFloat();
             contadore++;
             break;
             }
             
             case 2:{ 
             System.out.println("Informe Nº NFE");
             numeronfes[contadors] = scan.nextInt();
             System.out.println("Informe o nome do Produto");
             nomeprodutos[contadors] = scan.next();
             System.out.println("Informe a Quantidade de Produtos");
             qtdeprodutos[contadors] = scan.nextInt();
             precounits[contadors] = (precounite[contadore] *1.5f);
             System.out.println(precounits[contadors]);
             contadors++;
             break;
             }
             case 0:{
                terminou = true;
                break;
             } 
          
          }
          
          }  
                  
      }
} 



