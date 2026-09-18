package br.com.labmax.jmatrizbr;

/**
 * Representa uma matriz de valores do tipo {@code double}.
 *
 * <p>
 * Os índices de linhas e colunas começam em zero.
 * Todos os elementos são inicializados com {@code 0.0}.
 */
public class Matriz {
    private int linhas;
    private int colunas;
    private double[][] elementos;

    /**
     * Cria uma matriz com as dimensões informadas.
     *
     * @param linhas  quantidade de linhas, maior que zero
     * @param colunas quantidade de colunas, maior que zero
     * @throws IllegalArgumentException se linhas ou colunas
     *                                  forem menores ou iguais a zero
     */
    public Matriz(int linhas, int colunas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de linhas e colunas deve ser maior que zero.");
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
     * Retorna as dimensões da matriz.
     *
     * @return array com as dimensões da matriz(linhas, colunas).
     */
    public int[] getDimensoes() {
        return new int[]{getLinhas(), getColunas()};
    }

    /**
     * Retorna o elemento na posição informada.
     *
     * @param linha  índice da linha, de zero até {@code getLinhas() - 1}
     * @param coluna índice da coluna, de zero até {@code getColunas() - 1}
     * @return valor armazenado na posição
     * @throws IndexOutOfBoundsException se algum índice estiver
     *                                   fora dos limites da matriz
     */
    public double getElemento(int linha, int coluna) {
        validarIndices(linha, coluna);
        return elementos[linha][coluna];
    }

    /**
     * Altera o elemento na posição informada.
     *
     * @param linha  índice da linha, de zero até {@code getLinhas() - 1}
     * @param coluna índice da coluna, de zero até {@code getColunas() - 1}
     * @param valor  novo valor do elemento
     * @throws IndexOutOfBoundsException se algum índice estiver
     *                                   fora dos limites da matriz
     */
    public void setElemento(int linha, int coluna, double valor) {
        validarIndices(linha, coluna);
        elementos[linha][coluna] = valor;
    }

    /**
     * Valida os índices de uma posição da matriz.
     *
     * @param linha  índice da linha
     * @param coluna índice da coluna
     * @throws IndexOutOfBoundsException se algum índice estiver
     *                                   fora dos limites da matriz
     */
    private void validarIndices(int linha, int coluna) {
        if (linha < 0 || linha >= linhas) {
            throw new IndexOutOfBoundsException(
                    "Índice de linha inválido: " + linha
                            + ". Intervalo permitido: 0 a " + (linhas - 1) + ".");
        }

        if (coluna < 0 || coluna >= colunas) {
            throw new IndexOutOfBoundsException(
                    "Índice de coluna inválido: " + coluna
                            + ". Intervalo permitido: 0 a " + (colunas - 1) + ".");
        }
    }

    /**
     * Retorna a representação textual da matriz.
     *
     * <p>
     * Cada linha é apresentada entre colchetes, com os elementos
     * separados por vírgula e espaço.
     *
     * @return representação textual dos elementos da matriz
     */
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();

        for (int linha = 0; linha < linhas; linha++) {
            resultado.append("[");

            for (int coluna = 0; coluna < colunas; coluna++) {
                if (coluna > 0) {
                    resultado.append(", ");
                }

                resultado.append(elementos[linha][coluna]);
            }

            resultado.append("]");

            if (linha < linhas - 1) {
                resultado.append(System.lineSeparator());
            }
        }

        return resultado.toString();
    }

    /**
     * Soma esta matriz à matriz informada.
     *
     * <p>
     * As duas matrizes devem ter as mesmas dimensões.
     * O resultado é uma nova matriz; as originais não são alteradas.
     *
     * @param outra matriz a ser somada
     * @return nova matriz com a soma dos elementos correspondentes
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as dimensões forem diferentes
     */
    public Matriz somar(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser somada não pode ser nula.");
        }

        if (linhas != outra.linhas || colunas != outra.colunas) {
            throw new IllegalArgumentException(
                    "As matrizes devem ter as mesmas dimensões para a soma.");
        }

        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna]
                        + outra.elementos[linha][coluna];
            }
        }

        return resultado;
    }


    /**
     * Subtrai esta matriz à matriz informada.
     *
     * <p>
     * As duas matrizes devem ter as mesmas dimensões.
     * O resultado é uma nova matriz; as originais não são alteradas.
     *
     * @param outra matriz a ser subtraída
     * @return nova matriz com a subtração dos elementos correspondentes
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as dimensões forem diferentes
     */
    public Matriz subtrair(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser subtraída não pode ser nula.");
        }

        if (linhas != outra.linhas || colunas != outra.colunas) {
            throw new IllegalArgumentException(
                    "As matrizes devem ter as mesmas dimensões para a subtração.");
        }

        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna]
                        - outra.elementos[linha][coluna];
            }
        }

        return resultado;
    }
}