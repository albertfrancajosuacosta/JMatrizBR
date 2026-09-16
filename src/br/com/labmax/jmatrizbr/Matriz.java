package br.com.labmax.jmatrizbr;

public class Matriz {
    private int linhas;
    private int colunas;
    private double[][] elementos;

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.elementos = new double[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public double getElemento(int linha, int coluna) {
        return elementos[linha][coluna];
    }

    public void setElemento(int linha, int coluna, double valor) {
        elementos[linha][coluna] = valor;
    }
}
