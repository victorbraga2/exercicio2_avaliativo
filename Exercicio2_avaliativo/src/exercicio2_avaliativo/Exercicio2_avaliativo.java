/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio2_avaliativo;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class Exercicio2_avaliativo {

    public static void main(String[] args) {
    Scanner ler = new Scanner(System.in);
 
    double vlr;
    System.out.printf("Informe um valor entre 0 e 999: ");
    try {
      vlr = ler.nextDouble();
      System.out.printf("\nValor por extenso:\n");
      System.out.printf("%s\n", valorPorExtenso(vlr));
    } catch (InputMismatchException e) {
        System.out.printf("\nErro: valor informado incompatível.\n");
    }
  }
 
  public static String valorPorExtenso(double vlr) {
    if (vlr == 0)
       return("zero");
 
    long inteiro = (long)Math.abs(vlr); 
    double resto = vlr - inteiro;       
 
    String vlrS = String.valueOf(inteiro);
    if (vlrS.length() > 15)
       return("Erro: valor superior a 999.");
 
    String s = "", saux, vlrP;
    String centavos = String.valueOf((int)Math.round(resto * 100));
 
    String[] unidade = {"", "um", "dois", "três", "quatro", "cinco",
             "seis", "sete", "oito", "nove", "dez", "onze",
             "doze", "treze", "quatorze", "quinze", "dezesseis",
             "dezessete", "dezoito", "dezenove"};
    String[] centena = {"", "cento", "duzentos", "trezentos",
             "quatrocentos", "quinhentos", "seiscentos",
             "setecentos", "oitocentos", "novecentos"};
    String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
             "sessenta", "setenta", "oitenta", "noventa"};
    String[] qualificaS = {"", "mil", "milhão", "bilhão", "trilhão"};
    String[] qualificaP = {"", "mil", "milhões", "bilhões", "trilhões"};
 
    int n, unid, dez, cent, tam, i = 0;
    boolean umReal = false, tem = false;
    while (!vlrS.equals("0")) {
      tam = vlrS.length();

      if (tam > 3) {
         vlrP = vlrS.substring(tam-3, tam);
         vlrS = vlrS.substring(0, tam-3);
      }
      else { 
        vlrP = vlrS;
        vlrS = "0";
      }
      if (!vlrP.equals("000")) {
         saux = "";
         if (vlrP.equals("100"))
            saux = "cem";
         else {
           n = Integer.parseInt(vlrP, 10);  
           cent = n / 100;                  
           dez = (n % 100) / 10;            
           unid = (n % 100) % 10;           
           if (cent != 0)
              saux = centena[cent];
           if ((n % 100) <= 19) {
              if (saux.length() != 0)
                 saux = saux + " e " + unidade[n % 100];
              else saux = unidade[n % 100];
           }
           else {
              if (saux.length() != 0)
                 saux = saux + "" + dezena[dez];
              else saux = dezena[dez];
              if (unid != 0) {
                 if (saux.length() != 0)
                    saux = saux + " e " + unidade[unid];
                 else saux = unidade[unid];
              }
           }
         }
         if (vlrP.equals("1") || vlrP.equals("001")) {
            if (i == 0) 
               umReal = true;
            else saux = saux + " " + qualificaS[i];
         }
         else if (i != 0)
                 saux = saux + " " + qualificaP[i];
         if (s.length() != 0)
            s = saux + ", " + s;
         else s = saux;
      }
      if (((i == 0) || (i == 1)) && s.length() != 0)
         tem = true; 
      i = i + 1; 
    }
 
    if (s.length() != 0) {
       if (umReal)
          s = s + " ";
       else if (tem)
               s = s + " ";
            else s = s + " ";
    }
 

    if (!centavos.equals("0")) { 
       if (s.length() != 0) 
          s = s + " e ";
       if (centavos.equals("1"))
          s = s + "";
       else {
         n = Integer.parseInt(centavos, 10);
         if (n <= 19)
            s = s + unidade[n];
         else {             
           unid = n % 10;   
           dez = n / 10;    
           s = s + dezena[dez];
           if (unid != 0)
              s = s + " e " + unidade[unid];
         }
         s = s + " ";
       }
    }
    return(s);
  }
}