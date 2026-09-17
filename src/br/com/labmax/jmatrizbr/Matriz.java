package br.com.labmax.jmatrizbr;

/**
 * Representa uma matriz de valores do tipo {@code double}.
 *
 * <p>Os índices de linhas e colunas começam em zero.
 * Todos os elementos são inicializados com {@code 0.0}.
 */
public class Matriz {
    private int linhas;
    private int colunas;
    private double[][] elementos;

    /**
     * Cria uma matriz com as dimensões informadas.
     *
     * @param linhas quantidade de linhas, maior que zero
     * @param colunas quantidade de colunas, maior que zero
     * @throws IllegalArgumentException se linhas ou colunas
     *         forem menores ou iguais a zero
     */
    public Matriz(int linhas, int colunas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException(
                "A quantidade de linhas e colunas deve ser maior que zero."
            );
        }

        this.linhas = linhas;
        this.colunas = colunas;
        this.elementos = new double[linhas][colunas];
    }

    /**
     * Retorna a quantidade de linhas da matriz.
     *
     * @return quantidade de linhas
     */
    public int getLinhas() {
        return linhas;
    }

    /**
     * Retorna a quantidade de colunas da matriz.
     *
     * @return quantidade de colunas
     */
    public int getColunas() {
        return colunas;
    }

    /**
     * Retorna o elemento na posição informada.
     *
     * @param linha índice da linha, de zero até {@code getLinhas() - 1}
     * @param coluna índice da coluna, de zero até {@code getColunas() - 1}
     * @return valor armazenado na posição
     * @throws IndexOutOfBoundsException se algum índice estiver
     *         fora dos limites da matriz
     */
    public double getElemento(int linha, int coluna) {
        return elementos[linha][coluna];
    }

    /**
     * Altera o elemento na posição informada.
     *
     * @param linha índice da linha, de zero até {@code getLinhas() - 1}
     * @param coluna índice da coluna, de zero até {@code getColunas() - 1}
     * @param valor novo valor do elemento
     * @throws IndexOutOfBoundsException se algum índice estiver
     *         fora dos limites da matriz
     */
    public void setElemento(int linha, int coluna, double valor) {
        elementos[linha][coluna] = valor;
    }
}