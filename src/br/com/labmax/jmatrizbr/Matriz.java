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
        return new int[] { getLinhas(), getColunas() };
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

    /**
     * Multiplica cada elemento desta matriz pelo escalar informado.
     *
     * <p>
     * A matriz original não é alterada.
     *
     * @param escalar número pelo qual os elementos serão multiplicados
     * @return nova matriz com as mesmas dimensões e os elementos multiplicados
     */
    public Matriz multiplicarEscalar(double escalar) {
        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna] * escalar;
            }
        }

        return resultado;
    }

    /**
     * Multiplica esta matriz pela matriz informada, nesta ordem.
     *
     * <p>
     * O número de colunas desta matriz deve ser igual ao número
     * de linhas da outra matriz. As matrizes originais não são alteradas.
     *
     * @param outra matriz à direita na multiplicação
     * @return nova matriz com o número de linhas desta matriz
     *         e o número de colunas da outra matriz
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as dimensões forem incompatíveis
     */
    public Matriz multiplicar(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser multiplicada não pode ser nula.");
        }

        if (colunas != outra.linhas) {
            throw new IllegalArgumentException(
                    "O número de colunas da primeira matriz (" + colunas
                            + ") deve ser igual ao número de linhas da segunda ("
                            + outra.linhas + ").");
        }

        Matriz resultado = new Matriz(linhas, outra.colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < outra.colunas; coluna++) {
                double soma = 0.0;

                for (int k = 0; k < colunas; k++) {
                    soma += elementos[linha][k]
                            * outra.elementos[k][coluna];
                }

                resultado.elementos[linha][coluna] = soma;
            }
        }

        return resultado;
    }

    /**
     * Calcula a transposta desta matriz.
     *
     * <p>
     * As linhas tornam-se colunas e as colunas tornam-se linhas.
     * A matriz original não é alterada.
     *
     * @return nova matriz com as dimensões invertidas e os elementos transpostos
     */
    public Matriz transposta() {
        Matriz resultado = new Matriz(colunas, linhas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[coluna][linha] = elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Cria uma matriz identidade da ordem informada.
     *
     * <p>
     * A matriz possui o mesmo número de linhas e colunas,
     * com valores iguais a {@code 1.0} na diagonal principal
     * e {@code 0.0} nas demais posições.
     *
     * @param ordem quantidade de linhas e colunas, maior que zero
     * @return nova matriz identidade
     * @throws IllegalArgumentException se a ordem for menor ou igual a zero
     */
    public static Matriz identidade(int ordem) {
        Matriz resultado = new Matriz(ordem, ordem);

        for (int i = 0; i < ordem; i++) {
            resultado.elementos[i][i] = 1.0;
        }

        return resultado;
    }

    /**
     * Extrai a parte triangular superior desta matriz.
     *
     * <p>
     * Preserva os elementos da diagonal principal e acima dela,
     * substituindo por zero os elementos abaixo da diagonal.
     * A matriz original não é alterada.
     *
     * <p>
     * Também aceita matrizes retangulares, preservando as posições
     * cujo índice de coluna é maior ou igual ao índice de linha.
     *
     * @return nova matriz com as mesmas dimensões e a parte superior copiada
     */
    public Matriz triangularSuperior() {
        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = linha; coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Extrai a parte triangular inferior desta matriz.
     *
     * <p>
     * Preserva os elementos da diagonal principal e abaixo dela,
     * substituindo por zero os elementos acima da diagonal.
     * A matriz original não é alterada.
     *
     * <p>
     * Também aceita matrizes retangulares, preservando as posições
     * cujo índice de coluna é menor ou igual ao índice de linha.
     *
     * @return nova matriz com as mesmas dimensões e a parte inferior copiada
     */
    public Matriz triangularInferior() {
        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna <= linha && coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Calcula o determinante por eliminação de Gauss
     * com pivoteamento parcial.
     *
     * <p>
     * A matriz deve ser quadrada e conter apenas valores finitos.
     * A matriz original não é alterada.
     *
     * <p>
     * O resultado está sujeito a erros de arredondamento
     * da aritmética de ponto flutuante.
     *
     * @return determinante da matriz
     * @throws IllegalStateException se a matriz não for quadrada
     *                               ou contiver valores NaN ou infinitos
     */
    public double determinante() {
        if (linhas != colunas) {
            throw new IllegalStateException(
                    "O determinante exige uma matriz quadrada.");
        }

        int ordem = linhas;
        double[][] copia = new double[ordem][ordem];

        for (int linha = 0; linha < ordem; linha++) {
            for (int coluna = 0; coluna < ordem; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)) {
                    throw new IllegalStateException(
                            "O determinante exige elementos finitos.");
                }

                copia[linha][coluna] = valor;
            }
        }

        int sinal = 1;

        for (int pivo = 0; pivo < ordem; pivo++) {
            int linhaPivo = pivo;

            // Seleciona o maior valor absoluto na coluna.
            for (int linha = pivo + 1; linha < ordem; linha++) {
                if (Math.abs(copia[linha][pivo]) > Math.abs(copia[linhaPivo][pivo])) {
                    linhaPivo = linha;
                }
            }

            if (copia[linhaPivo][pivo] == 0.0) {
                return 0.0;
            }

            // A troca de linhas inverte o sinal do determinante.
            if (linhaPivo != pivo) {
                double[] temporaria = copia[pivo];
                copia[pivo] = copia[linhaPivo];
                copia[linhaPivo] = temporaria;
                sinal = -sinal;
            }

            // Elimina os elementos abaixo do pivô.
            for (int linha = pivo + 1; linha < ordem; linha++) {
                double fator = copia[linha][pivo] / copia[pivo][pivo];
                copia[linha][pivo] = 0.0;

                for (int coluna = pivo + 1; coluna < ordem; coluna++) {
                    copia[linha][coluna] -= fator * copia[pivo][coluna];
                }
            }
        }

        double resultado = sinal;

        for (int i = 0; i < ordem; i++) {
            resultado *= copia[i][i];
        }

        return resultado;
    }

    /**
     * Calcula a inversa por eliminação de Gauss-Jordan
     * com pivoteamento parcial.
     *
     * <p>
     * A matriz deve ser quadrada, não singular e conter
     * apenas valores finitos. A matriz original não é alterada.
     *
     * <p>
     * O algoritmo detecta pivôs exatamente nulos.
     * Matrizes próximas de singulares podem produzir resultados
     * imprecisos devido ao arredondamento de ponto flutuante.
     *
     * @return nova matriz contendo a inversa
     * @throws IllegalStateException se a matriz não for quadrada
     *                               ou contiver valores NaN ou infinitos
     * @throws ArithmeticException   se um pivô nulo for encontrado
     */
    public Matriz inversa() {
        if (linhas != colunas) {
            throw new IllegalStateException(
                    "A inversa exige uma matriz quadrada.");
        }

        int ordem = linhas;
        double[][] copia = new double[ordem][ordem];
        Matriz resultado = identidade(ordem);

        for (int linha = 0; linha < ordem; linha++) {
            for (int coluna = 0; coluna < ordem; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)) {
                    throw new IllegalStateException(
                            "A inversa exige elementos finitos.");
                }

                copia[linha][coluna] = valor;
            }
        }

        for (int pivo = 0; pivo < ordem; pivo++) {
            int linhaPivo = pivo;

            // Seleciona o maior valor absoluto na coluna.
            for (int linha = pivo + 1; linha < ordem; linha++) {
                if (Math.abs(copia[linha][pivo]) > Math.abs(copia[linhaPivo][pivo])) {
                    linhaPivo = linha;
                }
            }

            if (copia[linhaPivo][pivo] == 0.0) {
                throw new ArithmeticException(
                        "Não foi possível calcular a inversa: pivô nulo.");
            }

            // Aplica a troca às duas matrizes.
            if (linhaPivo != pivo) {
                double[] temporaria = copia[pivo];
                copia[pivo] = copia[linhaPivo];
                copia[linhaPivo] = temporaria;

                temporaria = resultado.elementos[pivo];
                resultado.elementos[pivo] = resultado.elementos[linhaPivo];
                resultado.elementos[linhaPivo] = temporaria;
            }

            // Divide a linha pelo pivô, tornando-o igual a 1.
            double valorPivo = copia[pivo][pivo];

            for (int coluna = 0; coluna < ordem; coluna++) {
                copia[pivo][coluna] /= valorPivo;
                resultado.elementos[pivo][coluna] /= valorPivo;
            }

            copia[pivo][pivo] = 1.0;

            // Zera a coluna do pivô nas demais linhas.
            for (int linha = 0; linha < ordem; linha++) {
                if (linha == pivo) {
                    continue;
                }

                double fator = copia[linha][pivo];

                for (int coluna = 0; coluna < ordem; coluna++) {
                    copia[linha][coluna] -= fator * copia[pivo][coluna];
                    resultado.elementos[linha][coluna] -= fator * resultado.elementos[pivo][coluna];
                }

                copia[linha][pivo] = 0.0;
            }
        }

        return resultado;
    }
}