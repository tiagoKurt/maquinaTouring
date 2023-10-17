package persistencia;

import java.util.HashMap;
import java.util.Map;


public class MaquinaTuring {
  private char[] fita; // Fita
  private int cabecote; // Posição do cabeçote
  private String estadoAtual; // Estado atual

  // Tabela de transição: Mapa de (estadoAtual, símboloLido) -> (novoSímbolo,
  // movimento, novoEstado)
  private Map<String, Map<Character, Transicao>> transicoes;

  public MaquinaTuring() {
    // Inicializa a fita, cabeçote e estado atual
    fita = new char[50];
    cabecote = 0;
    estadoAtual = "q0";

    // Inicializa a tabela de transição com as regras
    transicoes = new HashMap<>();
    transicoes.put("q0", Map.of(
        '0', new Transicao('1', 'D', "q1"),
        '1', new Transicao('0', 'D', "q2"),
        '#', new Transicao('x', 'D', "q4")));
    transicoes.put("q1", Map.of(
        '0', new Transicao('0', 'D', "q1"),
        '1', new Transicao('1', 'D', "q1"),
        'x', new Transicao('x', 'D', "q1"),
        '#', new Transicao('#', 'D', "q1"),
        'u', new Transicao('u', 'D', "q3")));
    transicoes.put("q2", Map.of(
        '0', new Transicao('0', 'D', "q2"),
        '1', new Transicao('1', 'D', "q2"),
        'x', new Transicao('x', 'D', "q2"),
        '#', new Transicao('#', 'D', "q2"),
        'u', new Transicao('u', 'D', "q3")));
    transicoes.put("q3", Map.of(
        '0', new Transicao('0', 'E', "q3"),
        '1', new Transicao('1', 'E', "q3"),
        'x', new Transicao('x', 'E', "q3"),
        '#', new Transicao('#', 'E', "q3"),
        'u', new Transicao('u', 'E', "q3"),
        ' ', new Transicao(' ', 'D', "q0")));
    transicoes.put("q4", Map.of(
        '0', new Transicao('0', 'D', "q4"),
        '1', new Transicao('1', 'D', "q4"),
        'x', new Transicao('x', 'D', "q4"),
        '#', new Transicao('#', 'D', "q4"),
        'u', new Transicao('u', 'D', "q4"),
        ' ', new Transicao(' ', 'D', "q0")));
  }

  public boolean executar() {
    while (!estadoAtual.equals("q0")) {
      char simboloAtual = fita[cabecote];
      Transicao transicao = transicoes.get(estadoAtual).get(simboloAtual);

      fita[cabecote] = transicao.getNovoSimbolo();// Escreve novo símbolo na fita
      moverCabecote(transicao.getDirecaoMovimento());// Move o cabeçote
      estadoAtual = transicao.getNovoEstado();// Muda o estado
    }
    return estadoAtual.equals("q0");
  }

  private void moverCabecote(char direcao) {
    // Move o cabeçote conforme a direção
    if (direcao == 'E') {
      cabecote--;
    } else if (direcao == 'D') {
      cabecote++;
    }
  }

  public void setFita(String entrada) {
    // Inicializa a fita com os primeiros caracteres da entrada
    for (int i = 0; i < fita.length; i++) {
      fita[i] = ' ';
    }
    // A entrada da fita ta na posição 0
    for (int i = 0; i < Math.min(entrada.length(), fita.length); i++) {
      fita[i] = entrada.charAt(i);
    }
  }

  class Transicao {

    private char novoSimbolo; // Símbolo a ser escrito na fita
    private char direcaoMovimento; // Direção para mover o cabeçote ('E' ou 'D')
    private String novoEstado; // Próximo estado

    public Transicao(char novoSimbolo, char direcaoMovimento, String novoEstado) {
      this.novoSimbolo = novoSimbolo;
      this.direcaoMovimento = direcaoMovimento;
      this.novoEstado = novoEstado;
    }

    public char getNovoSimbolo() {
      return novoSimbolo;
    }

    public char getDirecaoMovimento() {
      return direcaoMovimento;
    }

    public String getNovoEstado() {
      return novoEstado;
    }
  }
}