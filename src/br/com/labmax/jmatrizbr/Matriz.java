package br.com.labmax.jmatrizbr;

import java.util.Locale;

/**
 * Representa uma matriz de valores do tipo {@code double}.
 *
 * <p>
 * Os índices de linhas e colunas começam em zero.
 * A matriz pode ser criada com elementos zerados ou
 * a partir de uma cópia dos valores de um array bidimensional.
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
     * Cria uma matriz copiando os valores do array informado.
     *
     * <p>
     * O array deve possuir pelo menos uma linha e uma coluna.
     * Todas as linhas devem ser não nulas e ter o mesmo tamanho.
     *
     * <p>
     * Os dados são copiados: alterações posteriores no array
     * recebido não modificam a matriz, e vice-versa.
     *
     * @param valores array bidimensional com os valores iniciais
     * @throws NullPointerException     se o array ou alguma linha for nulo
     * @throws IllegalArgumentException se o array não possuir linhas
     *                                  ou colunas, ou se as linhas tiverem tamanhos
     *                                  diferentes
     */
    public Matriz(double[][] valores) {
        if (valores == null) {
            throw new NullPointerException(
                    "O array de valores não pode ser nulo.");
        }

        if (valores.length == 0) {
            throw new IllegalArgumentException(
                    "A matriz deve possuir pelo menos uma linha.");
        }

        if (valores[0] == null) {
            throw new NullPointerException(
                    "A linha 0 não pode ser nula.");
        }

        int quantidadeColunas = valores[0].length;

        if (quantidadeColunas == 0) {
            throw new IllegalArgumentException(
                    "A matriz deve possuir pelo menos uma coluna.");
        }

        for (int linha = 0; linha < valores.length; linha++) {
            if (valores[linha] == null) {
                throw new NullPointerException(
                        "A linha " + linha + " não pode ser nula.");
            }

            if (valores[linha].length != quantidadeColunas) {
                throw new IllegalArgumentException(
                        "Todas as linhas devem ter a mesma quantidade de colunas.");
            }
        }

        this.linhas = valores.length;
        this.colunas = quantidadeColunas;
        this.elementos = new double[linhas][colunas];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                this.elementos[linha][coluna] = valores[linha][coluna];
            }
        }
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

    /**
     * Cria uma cópia independente desta matriz.
     *
     * <p>
     * A cópia possui as mesmas dimensões e valores.
     * Alterações em uma matriz não afetam a outra.
     *
     * @return nova matriz com uma cópia de todos os elementos
     */
    public Matriz copiar() {
        return new Matriz(elementos);
    }

    /**
     * Retorna uma cópia dos elementos da linha informada.
     *
     * <p>
     * Alterações no array retornado não modificam a matriz,
     * e alterações na matriz não modificam o array retornado.
     *
     * @param linha índice da linha, de zero até {@code getLinhas() - 1}
     * @return array independente com os elementos da linha
     * @throws IndexOutOfBoundsException se a linha estiver fora dos limites
     */
    public double[] getLinha(int linha) {
        validarIndices(linha, 0);

        double[] copia = new double[colunas];

        for (int coluna = 0; coluna < colunas; coluna++) {
            copia[coluna] = elementos[linha][coluna];
        }

        return copia;
    }

    /**
     * Retorna uma cópia dos elementos da coluna informada.
     *
     * <p>
     * Alterações no array retornado não modificam a matriz,
     * e alterações na matriz não modificam o array retornado.
     *
     * @param coluna índice da coluna, de zero até {@code getColunas() - 1}
     * @return array independente com os elementos da coluna
     * @throws IndexOutOfBoundsException se a coluna estiver fora dos limites
     */
    public double[] getColuna(int coluna) {
        validarIndices(0, coluna);

        double[] copia = new double[linhas];

        for (int linha = 0; linha < linhas; linha++) {
            copia[linha] = elementos[linha][coluna];
        }

        return copia;
    }

    /**
     * Retorna uma cópia profunda dos elementos desta matriz.
     *
     * <p>
     * O array retornado e suas linhas são independentes
     * do armazenamento interno. Alterações na cópia não modificam
     * a matriz, e vice-versa.
     *
     * @return array bidimensional com uma cópia de todos os elementos
     */
    public double[][] getElementos() {
        double[][] copia = new double[linhas][colunas];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                copia[linha][coluna] = elementos[linha][coluna];
            }
        }

        return copia;
    }

    /**
     * Retorna uma cópia dos elementos da diagonal principal.
     *
     * <p>
     * Também aceita matrizes retangulares. O tamanho do array
     * retornado é o menor entre a quantidade de linhas e colunas.
     *
     * <p>
     * Alterações no array retornado não modificam a matriz,
     * e alterações na matriz não modificam o array retornado.
     *
     * @return array independente com os elementos da diagonal principal
     */
    public double[] getDiagonalPrincipal() {
        int tamanho = Math.min(linhas, colunas);
        double[] diagonal = new double[tamanho];

        for (int i = 0; i < tamanho; i++) {
            diagonal[i] = elementos[i][i];
        }

        return diagonal;
    }

    /**
     * Verifica se esta matriz é aproximadamente igual à informada.
     *
     * <p>
     * As dimensões devem ser iguais. Cada par de elementos finitos
     * deve atender à tolerância absoluta ou à tolerância relativa,
     * calculada em relação ao maior valor absoluto do par.
     *
     * <p>
     * Valores NaN nunca são considerados iguais. Valores infinitos
     * são considerados iguais apenas quando possuem o mesmo sinal.
     *
     * @param outra              matriz a ser comparada
     * @param toleranciaAbsoluta diferença absoluta máxima permitida
     * @param toleranciaRelativa diferença relativa máxima permitida
     * @return {@code true} se todos os elementos forem aproximadamente
     *         iguais; {@code false} se houver diferença nas dimensões
     *         ou algum par não atender aos critérios
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se alguma tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean aproximadamenteIgual(
            Matriz outra,
            double toleranciaAbsoluta,
            double toleranciaRelativa) {

        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser comparada não pode ser nula.");
        }

        if (!Double.isFinite(toleranciaAbsoluta)
                || toleranciaAbsoluta < 0.0
                || !Double.isFinite(toleranciaRelativa)
                || toleranciaRelativa < 0.0) {
            throw new IllegalArgumentException(
                    "As tolerâncias devem ser finitas e não negativas.");
        }

        if (linhas != outra.linhas || colunas != outra.colunas) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double a = elementos[linha][coluna];
                double b = outra.elementos[linha][coluna];

                if (Double.isNaN(a) || Double.isNaN(b)) {
                    return false;
                }

                // Inclui valores idênticos e infinitos de mesmo sinal.
                if (a == b) {
                    continue;
                }

                if (!Double.isFinite(a) || !Double.isFinite(b)) {
                    return false;
                }

                double diferenca = Math.abs(a - b);

                if (diferenca <= toleranciaAbsoluta) {
                    continue;
                }

                double escala = Math.max(Math.abs(a), Math.abs(b));

                // Evita transbordamento na diferença de valores extremos.
                double diferencaRelativa = Double.isFinite(diferenca)
                        ? diferenca / escala
                        : Math.abs(a / escala - b / escala);

                if (diferencaRelativa > toleranciaRelativa) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se esta matriz é quadrada.
     *
     * @return {@code true} se a quantidade de linhas for igual
     *         à quantidade de colunas; {@code false} caso contrário
     */
    public boolean isQuadrada() {
        return linhas == colunas;
    }

    /**
     * Valida uma tolerância absoluta.
     *
     * @param tolerancia tolerância a ser validada
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    private void validarTolerancia(double tolerancia) {
        if (!Double.isFinite(tolerancia) || tolerancia < 0.0) {
            throw new IllegalArgumentException(
                    "A tolerância deve ser finita e não negativa.");
        }
    }

    /**
     * Verifica se todos os elementos são aproximadamente zero.
     *
     * @param tolerancia valor absoluto máximo permitido por elemento
     * @return {@code true} se todos os elementos forem finitos
     *         e tiverem valor absoluto menor ou igual à tolerância
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean isNula(double tolerancia) {
        validarTolerancia(tolerancia);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)
                        || Math.abs(valor) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se esta matriz é aproximadamente uma identidade.
     *
     * @param tolerancia diferença absoluta máxima em relação
     *                   ao valor esperado em cada posição
     * @return {@code true} se a matriz for quadrada, contiver apenas
     *         valores finitos e atender ao padrão da identidade
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean isIdentidade(double tolerancia) {
        validarTolerancia(tolerancia);

        if (!isQuadrada()) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];
                double esperado = linha == coluna ? 1.0 : 0.0;

                if (!Double.isFinite(valor)
                        || Math.abs(valor - esperado) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se esta matriz é aproximadamente simétrica.
     *
     * @param tolerancia diferença absoluta máxima entre
     *                   elementos em posições transpostas
     * @return {@code true} se a matriz for quadrada, contiver apenas
     *         valores finitos e os pares atenderem à tolerância
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean isSimetrica(double tolerancia) {
        validarTolerancia(tolerancia);

        if (!isQuadrada()) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            if (!Double.isFinite(elementos[linha][linha])) {
                return false;
            }

            for (int coluna = linha + 1; coluna < colunas; coluna++) {
                double a = elementos[linha][coluna];
                double b = elementos[coluna][linha];

                if (!Double.isFinite(a)
                        || !Double.isFinite(b)
                        || Math.abs(a - b) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se esta matriz é aproximadamente diagonal.
     *
     * @param tolerancia valor absoluto máximo permitido
     *                   para elementos fora da diagonal principal
     * @return {@code true} se a matriz for quadrada, contiver apenas
     *         valores finitos e os elementos fora da diagonal
     *         forem aproximadamente zero
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean isDiagonal(double tolerancia) {
        validarTolerancia(tolerancia);

        if (!isQuadrada()) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)) {
                    return false;
                }

                if (linha != coluna && Math.abs(valor) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se esta matriz é aproximadamente triangular superior.
     *
     * @param tolerancia valor absoluto máximo permitido
     *                   para elementos abaixo da diagonal principal
     * @return {@code true} se a matriz for quadrada, contiver apenas
     *         valores finitos e os elementos abaixo da diagonal
     *         forem aproximadamente zero
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean isTriangularSuperior(double tolerancia) {
        validarTolerancia(tolerancia);

        if (!isQuadrada()) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)) {
                    return false;
                }

                if (linha > coluna && Math.abs(valor) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se esta matriz é aproximadamente triangular inferior.
     *
     * @param tolerancia valor absoluto máximo permitido
     *                   para elementos acima da diagonal principal
     * @return {@code true} se a matriz for quadrada, contiver apenas
     *         valores finitos e os elementos acima da diagonal
     *         forem aproximadamente zero
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  NaN ou infinita
     */
    public boolean isTriangularInferior(double tolerancia) {
        validarTolerancia(tolerancia);

        if (!isQuadrada()) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)) {
                    return false;
                }

                if (linha < coluna && Math.abs(valor) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Calcula o traço desta matriz.
     *
     * <p>
     * O traço é a soma dos elementos da diagonal principal.
     * A matriz original não é alterada.
     *
     * @return soma dos elementos da diagonal principal
     * @throws IllegalStateException se a matriz não for quadrada
     */
    public double traco() {
        if (!isQuadrada()) {
            throw new IllegalStateException(
                    "O cálculo do traço exige uma matriz quadrada.");
        }

        double soma = 0.0;

        for (int i = 0; i < linhas; i++) {
            soma += elementos[i][i];
        }

        return soma;
    }

    /**
     * Divide cada elemento desta matriz pelo escalar informado.
     *
     * <p>
     * A matriz original não é alterada.
     * O divisor deve ser finito e diferente de zero.
     *
     * @param divisor número pelo qual os elementos serão divididos
     * @return nova matriz com as mesmas dimensões e os elementos divididos
     * @throws IllegalArgumentException se o divisor for NaN ou infinito
     * @throws ArithmeticException      se o divisor for zero
     */
    public Matriz dividirEscalar(double divisor) {
        if (!Double.isFinite(divisor)) {
            throw new IllegalArgumentException(
                    "O divisor deve ser um número finito.");
        }

        if (divisor == 0.0) {
            throw new ArithmeticException(
                    "Não é possível dividir por zero.");
        }

        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna] / divisor;
            }
        }

        return resultado;
    }

    /**
     * Eleva esta matriz a um expoente inteiro não negativo.
     *
     * <p>
     * A matriz deve ser quadrada. Para expoente zero,
     * retorna a identidade; para expoente um, retorna uma cópia.
     * A matriz original não é alterada.
     *
     * <p>
     * Utiliza exponenciação por quadrados.
     *
     * @param expoente expoente inteiro não negativo
     * @return nova matriz contendo o resultado da potenciação
     * @throws IllegalArgumentException se o expoente for negativo
     * @throws IllegalStateException    se a matriz não for quadrada
     */
    public Matriz potencia(int expoente) {
        if (expoente < 0) {
            throw new IllegalArgumentException(
                    "O expoente deve ser maior ou igual a zero.");
        }

        if (!isQuadrada()) {
            throw new IllegalStateException(
                    "A potenciação exige uma matriz quadrada.");
        }

        if (expoente == 0) {
            return identidade(linhas);
        }

        Matriz base = copiar();
        Matriz resultado = null;
        int restante = expoente;

        while (restante > 0) {
            if (restante % 2 != 0) {
                resultado = resultado == null
                        ? base
                        : resultado.multiplicar(base);
            }

            restante /= 2;

            if (restante > 0) {
                base = base.multiplicar(base);
            }
        }

        return resultado;
    }

    /**
     * Compara esta matriz com outro objeto.
     *
     * <p>
     * A comparação considera as dimensões e os valores exatos
     * dos elementos.
     *
     * @param objeto objeto a ser comparado
     * @return {@code true} se os objetos representarem matrizes iguais
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Matriz outra)) {
            return false;
        }

        return igualExata(outra);
    }

    /**
     * Calcula o código hash da matriz.
     *
     * @return código hash baseado nas dimensões e nos elementos
     */
    @Override
    public int hashCode() {
        int resultado = 17;

        resultado = 31 * resultado + linhas;
        resultado = 31 * resultado + colunas;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                long bits = Double.doubleToLongBits(
                        elementos[linha][coluna]);

                resultado = 31 * resultado
                        + (int) (bits ^ (bits >>> 32));
            }
        }

        return resultado;
    }

    /**
     * Verifica se outra matriz possui exatamente as mesmas dimensões
     * e os mesmos valores.
     *
     * @param outra matriz a ser comparada
     * @return {@code true} se as matrizes forem exatamente iguais
     * @throws NullPointerException se a matriz informada for nula
     */
    public boolean igualExata(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser comparada não pode ser nula.");
        }

        if (linhas != outra.linhas || colunas != outra.colunas) {
            return false;
        }

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                if (Double.doubleToLongBits(
                        elementos[linha][coluna]) != Double.doubleToLongBits(
                                outra.elementos[linha][coluna])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Retorna uma representação textual da matriz com a quantidade
     * de casas decimais informada.
     *
     * @param casasDecimais quantidade de casas decimais
     * @return matriz formatada
     * @throws IllegalArgumentException se a quantidade de casas
     *                                  decimais for negativa
     */
    public String formatar(int casasDecimais) {
        if (casasDecimais < 0) {
            throw new IllegalArgumentException(
                    "A quantidade de casas decimais não pode ser negativa.");
        }

        String formato = "%." + casasDecimais + "f";
        StringBuilder resultado = new StringBuilder();

        for (int linha = 0; linha < linhas; linha++) {
            resultado.append("[");

            for (int coluna = 0; coluna < colunas; coluna++) {
                if (coluna > 0) {
                    resultado.append(", ");
                }

                resultado.append(String.format(
                        Locale.ROOT,
                        formato,
                        elementos[linha][coluna]));
            }

            resultado.append("]");

            if (linha < linhas - 1) {
                resultado.append(System.lineSeparator());
            }
        }

        return resultado.toString();
    }

    /**
     * Calcula a soma de todos os elementos da matriz.
     *
     * @return soma total dos elementos
     */
    public double somaTotal() {
        double soma = 0.0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                soma += elementos[linha][coluna];
            }
        }

        return soma;
    }
}