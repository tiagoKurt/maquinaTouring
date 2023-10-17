package main;

import java.util.Scanner;

import persistencia.MaquinaTuring;


public class maquinaTuringMain {
  public static void main(String[] args) {
    MaquinaTuring maquinaTuring = new MaquinaTuring();
    Scanner scanner = new Scanner(System.in);
    String entrada = "";

    // Solicitar entrada até que seja uma sequência válida
    boolean entradaValida = false;
    while (!entradaValida) {
      System.out.print("Digite a entrada (composta por 0s, 1s ou #): ");
      entrada = scanner.nextLine();

      // Verificar se a entrada contém apenas símbolos válidos
      entradaValida = entrada.matches("[01#]+");

      if (!entradaValida) {
        System.out.println("Entrada inválida. A entrada deve conter apenas 0s, 1s ou #");
      }
    }
    // Define a entrada na fita e executa a máquina de Turing
    maquinaTuring.setFita(entrada);
    boolean aceitouEntrada = maquinaTuring.executar();

    // Exibe se a máquina aceitou ou não a entrada
    if (aceitouEntrada) {
      System.out.println("Máquina de Turing aceitou a entrada.");
    } else {
      System.out.println("Máquina de Turing não aceitou a entrada.");
    }
    scanner.close();
  }
}
