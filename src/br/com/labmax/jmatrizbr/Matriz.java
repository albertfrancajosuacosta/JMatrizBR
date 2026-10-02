package br.com.labmax.jmatrizbr;


import java.util.Locale;
import br.com.labmax.jmatrizbr.algebra.EspacoVetorial;

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
     * Retorna uma cópia dos elementos da diagonal secundária.
     *
     * <p>
     * Também aceita matrizes retangulares. O tamanho do array
     * retornado é o menor entre a quantidade de linhas e colunas.
     *
     * <p>
     * Alterações no array retornado não modificam a matriz,
     * e alterações na matriz não modificam o array retornado.
     *
     * <p>
     * O percurso começa no canto superior direito e avança uma linha
     * para baixo e uma coluna para esquerda a cada passo,
     * a última linha ou a primeira coluna.
     * 
     * @return array independente com os elementos da diagonal secundária
     */
    public double[] getDiagonalSecundaria() {
        int tamanho = Math.min(linhas, colunas);
        double[] diagonalSecundaria = new double[tamanho];

        for (int i = 0; i < tamanho; i++) {
            diagonalSecundaria[i] = elementos[i][colunas - 1 - i];
        }

        return diagonalSecundaria;
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
     * Substitui os elementos da linha informada.
     *
     * <p>
     * O array deve conter exatamente a quantidade de colunas
     * da matriz. Os valores são copiados; alterações posteriores
     * no array não modificam a matriz.
     *
     * @param linha   índice da linha, de zero até {@code getLinhas() - 1}
     * @param valores novos valores da linha
     * @throws IndexOutOfBoundsException se a linha estiver fora dos limites
     * @throws NullPointerException      se o array de valores for nulo
     * @throws IllegalArgumentException  se o tamanho do array for diferente
     *                                   da quantidade de colunas
     */
    public void setLinha(int linha, double[] valores) {
        validarIndices(linha, 0);

        if (valores == null) {
            throw new NullPointerException(
                    "O array de valores da linha não pode ser nulo.");
        }

        if (valores.length != colunas) {
            throw new IllegalArgumentException(
                    "O array deve conter " + colunas
                            + " elementos, mas contém " + valores.length + ".");
        }

        for (int coluna = 0; coluna < colunas; coluna++) {
            elementos[linha][coluna] = valores[coluna];
        }
    }

    /**
     * Substitui os elementos da coluna informada.
     *
     * <p>
     * O array deve conter exatamente a quantidade de linhas
     * da matriz. Os valores são copiados; alterações posteriores
     * no array não modificam a matriz.
     *
     * @param coluna  índice da coluna, de zero até {@code getColunas() - 1}
     * @param valores novos valores da coluna
     * @throws IndexOutOfBoundsException se a coluna estiver fora dos limites
     * @throws NullPointerException      se o array de valores for nulo
     * @throws IllegalArgumentException  se o tamanho do array for diferente
     *                                   da quantidade de linhas
     */
    public void setColuna(int coluna, double[] valores) {
        validarIndices(0, coluna);

        if (valores == null) {
            throw new NullPointerException(
                    "O array de valores da coluna não pode ser nulo.");
        }

        if (valores.length != linhas) {
            throw new IllegalArgumentException(
                    "O array deve conter " + linhas
                            + " elementos, mas contém " + valores.length + ".");
        }

        for (int linha = 0; linha < linhas; linha++) {
            elementos[linha][coluna] = valores[linha];
        }
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

    /**
     * Preenche a diagonal principal com o valor informado.
     *
     * <p>
     * Também aceita matrizes retangulares. A quantidade de elementos
     * alterados é o menor valor entre a quantidade de linhas e colunas.
     * Os elementos fora da diagonal principal permanecem inalterados.
     *
     * @param valor valor a ser atribuído aos elementos da diagonal principal
     */
    public void preencherDiagonal(double valor) {
        int tamanho = Math.min(linhas, colunas);

        for (int indice = 0; indice < tamanho; indice++) {
            elementos[indice][indice] = valor;
        }
    }

    /**
     * Troca os elementos de duas linhas da matriz.
     *
     * <p>
     * Se os índices forem iguais, a matriz permanece inalterada.
     *
     * @param linhaA índice da primeira linha
     * @param linhaB índice da segunda linha
     * @throws IndexOutOfBoundsException se algum índice estiver
     *                                   fora dos limites da matriz
     */
    public void trocarLinhas(int linhaA, int linhaB) {
        validarIndices(linhaA, 0);
        validarIndices(linhaB, 0);

        if (linhaA == linhaB) {
            return;
        }

        double[] temporaria = elementos[linhaA];
        elementos[linhaA] = elementos[linhaB];
        elementos[linhaB] = temporaria;
    }

    /**
     * Troca os elementos de duas colunas da matriz.
     *
     * <p>
     * Se os índices forem iguais, a matriz permanece inalterada.
     *
     * @param colunaA índice da primeira coluna
     * @param colunaB índice da segunda coluna
     * @throws IndexOutOfBoundsException se algum índice estiver
     *                                   fora dos limites da matriz
     */
    public void trocarColunas(int colunaA, int colunaB) {
        validarIndices(0, colunaA);
        validarIndices(0, colunaB);

        if (colunaA == colunaB) {
            return;
        }

        for (int linha = 0; linha < linhas; linha++) {
            double temporario = elementos[linha][colunaA];
            elementos[linha][colunaA] = elementos[linha][colunaB];
            elementos[linha][colunaB] = temporario;
        }
    }

    /**
     * Verifica a consistência das dimensões e do armazenamento interno.
     *
     * <p>
     * As dimensões devem ser positivas, o array de elementos deve
     * possuir a quantidade de linhas declarada e cada linha deve
     * possuir a quantidade de colunas declarada.
     *
     * @throws IllegalStateException se as dimensões ou o armazenamento
     *                               interno estiverem inconsistentes
     */
    private void validarDimensoes() {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalStateException(
                    "As quantidades de linhas e colunas devem ser maiores que zero.");
        }

        if (elementos == null) {
            throw new IllegalStateException(
                    "O armazenamento interno da matriz não pode ser nulo.");
        }

        if (elementos.length != linhas) {
            throw new IllegalStateException(
                    "A quantidade de linhas armazenadas difere da dimensão declarada.");
        }

        for (int linha = 0; linha < linhas; linha++) {
            if (elementos[linha] == null) {
                throw new IllegalStateException(
                        "A linha " + linha + " do armazenamento interno é nula.");
            }

            if (elementos[linha].length != colunas) {
                throw new IllegalStateException(
                        "A linha " + linha + " possui "
                                + elementos[linha].length
                                + " elementos, mas deveria possuir " + colunas + ".");
            }
        }
    }

    /**
     * Calcula o produto de Hadamard entre esta matriz e a matriz informada.
     *
     * <p>
     * Multiplica os elementos que ocupam posições correspondentes.
     * As duas matrizes devem possuir as mesmas dimensões.
     *
     * <p>
     * As matrizes originais não são alteradas.
     *
     * @param outra matriz cujos elementos serão multiplicados pelos desta matriz
     * @return nova matriz com os produtos dos elementos correspondentes
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as dimensões forem diferentes
     */
    public Matriz produtoHadamard(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz para o produto de Hadamard não pode ser nula.");
        }

        if (linhas != outra.linhas || colunas != outra.colunas) {
            throw new IllegalArgumentException(
                    "As matrizes devem ter as mesmas dimensões "
                            + "para o produto de Hadamard.");
        }

        Matriz resultado = new Matriz(linhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[linha][coluna] = elementos[linha][coluna] * outra.elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Calcula o produto de Kronecker entre esta matriz e a matriz informada.
     *
     * <p>
     * Cada elemento desta matriz multiplica toda a outra matriz,
     * formando um bloco na matriz resultante.
     *
     * <p>
     * Se esta matriz possui dimensões m por n e a outra possui
     * dimensões p por q, o resultado possui dimensões (m * p) por (n * q).
     * Não é necessário que as matrizes tenham dimensões iguais.
     *
     * <p>
     * As matrizes originais não são alteradas.
     *
     * @param outra matriz utilizada para formar os blocos do resultado
     * @return nova matriz com o produto de Kronecker
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se alguma dimensão do resultado
     *                                  exceder o maior valor permitido pelo tipo
     *                                  {@code int}
     */
    public Matriz produtoKronecker(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz para o produto de Kronecker não pode ser nula.");
        }

        // Usa long para verificar as dimensões sem causar overflow de int.
        long totalLinhas = (long) linhas * outra.linhas;
        long totalColunas = (long) colunas * outra.colunas;

        if (totalLinhas > Integer.MAX_VALUE
                || totalColunas > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "As dimensões do produto de Kronecker excedem "
                            + "o limite permitido pelo tipo int.");
        }

        Matriz resultado = new Matriz(
                (int) totalLinhas, (int) totalColunas);

        for (int linhaA = 0; linhaA < linhas; linhaA++) {
            for (int colunaA = 0; colunaA < colunas; colunaA++) {
                double fator = elementos[linhaA][colunaA];

                // Localiza o início do bloco correspondente ao elemento de A.
                int inicioLinha = linhaA * outra.linhas;
                int inicioColuna = colunaA * outra.colunas;

                for (int linhaB = 0; linhaB < outra.linhas; linhaB++) {
                    for (int colunaB = 0; colunaB < outra.colunas; colunaB++) {
                        resultado.elementos[inicioLinha + linhaB][inicioColuna + colunaB] = fator
                                * outra.elementos[linhaB][colunaB];
                    }
                }
            }
        }

        return resultado;
    }

    /**
     * Calcula a média aritmética de todos os elementos.
     *
     * @return média dos elementos
     */
    public double media() {
        return somaTotal() / ((long) linhas * colunas);
    }

    /**
     * Calcula a mediana de todos os elementos.
     *
     * <p>
     * Os valores são ordenados em uma cópia, sem alterar esta matriz.
     * Quando a quantidade de elementos é par, retorna a média
     * dos dois valores centrais.
     *
     * @return mediana, ou {@code Double.NaN} se houver algum NaN
     * @throws IllegalStateException se a quantidade de elementos
     *                               exceder o limite de um array indexado por
     *                               {@code int}
     */
    public double mediana() {
        long quantidade = (long) linhas * colunas;

        if (quantidade > Integer.MAX_VALUE) {
            throw new IllegalStateException(
                    "A quantidade de elementos excede o limite de um array.");
        }

        double[] valores = new double[(int) quantidade];
        int indice = 0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];

                if (Double.isNaN(valor)) {
                    return Double.NaN;
                }

                valores[indice++] = valor;
            }
        }

        java.util.Arrays.sort(valores);

        int meio = valores.length / 2;

        if (valores.length % 2 != 0) {
            return valores[meio];
        }

        double primeiro = valores[meio - 1];
        double segundo = valores[meio];

        // Preserva valores iguais, inclusive infinitos de mesmo sinal.
        if (primeiro == segundo) {
            return primeiro;
        }

        // Evita overflow na soma de dois valores finitos de mesmo sinal.
        if (Double.isFinite(primeiro)
                && Double.isFinite(segundo)
                && Math.signum(primeiro) == Math.signum(segundo)) {
            return primeiro + (segundo - primeiro) / 2.0;
        }

        return (primeiro + segundo) / 2.0;
    }

    /**
     * Calcula a variância populacional dos elementos.
     *
     * <p>
     * Utiliza o algoritmo incremental de Welford, que evita
     * calcular a variância pela diferença entre grandes somas.
     * O divisor é a quantidade total de elementos.
     *
     * @return variância populacional, ou {@code Double.NaN}
     *         se houver valores não finitos
     */
    public double variancia() {
        long quantidade = 0;
        double mediaAtual = 0.0;
        double somaDesvios = 0.0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double valor = elementos[linha][coluna];

                if (!Double.isFinite(valor)) {
                    return Double.NaN;
                }

                quantidade++;

                double diferenca = valor - mediaAtual;
                mediaAtual += diferenca / quantidade;

                double diferencaAtualizada = valor - mediaAtual;
                somaDesvios += diferenca * diferencaAtualizada;
            }
        }

        return somaDesvios / quantidade;
    }

    /**
     * Calcula o desvio padrão populacional dos elementos.
     *
     * @return raiz quadrada da variância populacional
     */
    public double desvioPadrao() {
        return Math.sqrt(variancia());
    }

    /**
     * Retorna o menor elemento da matriz.
     *
     * @return menor valor, ou {@code Double.NaN} se houver algum NaN
     */
    public double minimo() {
        double resultado = elementos[0][0];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado = Math.min(resultado, elementos[linha][coluna]);
            }
        }

        return resultado;
    }

    /**
     * Retorna o maior elemento da matriz.
     *
     * @return maior valor, ou {@code Double.NaN} se houver algum NaN
     */
    public double maximo() {
        double resultado = elementos[0][0];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado = Math.max(resultado, elementos[linha][coluna]);
            }
        }

        return resultado;
    }

    /**
     * Calcula a soma dos elementos de cada linha.
     *
     * @return novo array cuja posição i contém a soma da linha i
     */
    public double[] somaPorLinha() {
        double[] resultado = new double[linhas];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado[linha] += elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Calcula a soma dos elementos de cada coluna.
     *
     * @return novo array cuja posição j contém a soma da coluna j
     */
    public double[] somaPorColuna() {
        double[] resultado = new double[colunas];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado[coluna] += elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Calcula a média aritmética de cada linha.
     *
     * @return novo array cuja posição i contém a média da linha i
     */
    public double[] mediaPorLinha() {
        double[] resultado = somaPorLinha();

        for (int linha = 0; linha < linhas; linha++) {
            resultado[linha] /= colunas;
        }

        return resultado;
    }

    /**
     * Calcula a média aritmética de cada coluna.
     *
     * @return novo array cuja posição j contém a média da coluna j
     */
    public double[] mediaPorColuna() {
        double[] resultado = somaPorColuna();

        for (int coluna = 0; coluna < colunas; coluna++) {
            resultado[coluna] /= linhas;
        }

        return resultado;
    }

    /**
     * Retorna o menor elemento de cada linha.
     *
     * @return novo array com os mínimos; linhas com NaN retornam NaN
     */
    public double[] minimoPorLinha() {
        double[] resultado = new double[linhas];

        for (int linha = 0; linha < linhas; linha++) {
            resultado[linha] = elementos[linha][0];

            for (int coluna = 1; coluna < colunas; coluna++) {
                resultado[linha] = Math.min(
                        resultado[linha], elementos[linha][coluna]);
            }
        }

        return resultado;
    }

    /**
     * Retorna o maior elemento de cada linha.
     *
     * @return novo array com os máximos; linhas com NaN retornam NaN
     */
    public double[] maximoPorLinha() {
        double[] resultado = new double[linhas];

        for (int linha = 0; linha < linhas; linha++) {
            resultado[linha] = elementos[linha][0];

            for (int coluna = 1; coluna < colunas; coluna++) {
                resultado[linha] = Math.max(
                        resultado[linha], elementos[linha][coluna]);
            }
        }

        return resultado;
    }

    /**
     * Retorna o menor elemento de cada coluna.
     *
     * @return novo array com os mínimos; colunas com NaN retornam NaN
     */
    public double[] minimoPorColuna() {
        double[] resultado = new double[colunas];

        for (int coluna = 0; coluna < colunas; coluna++) {
            resultado[coluna] = elementos[0][coluna];

            for (int linha = 1; linha < linhas; linha++) {
                resultado[coluna] = Math.min(
                        resultado[coluna], elementos[linha][coluna]);
            }
        }

        return resultado;
    }

    /**
     * Retorna o maior elemento de cada coluna.
     *
     * @return novo array com os máximos; colunas com NaN retornam NaN
     */
    public double[] maximoPorColuna() {
        double[] resultado = new double[colunas];

        for (int coluna = 0; coluna < colunas; coluna++) {
            resultado[coluna] = elementos[0][coluna];

            for (int linha = 1; linha < linhas; linha++) {
                resultado[coluna] = Math.max(
                        resultado[coluna], elementos[linha][coluna]);
            }
        }

        return resultado;
    }

    /**
     * Conta os elementos iguais ou próximos ao valor informado.
     *
     * <p>
     * Para valores finitos, utiliza uma tolerância absoluta:
     * a diferença absoluta deve ser menor ou igual à tolerância.
     * Infinitos são considerados iguais apenas quando possuem o mesmo sinal.
     * Os valores {@code +0.0} e {@code -0.0} são considerados iguais.
     *
     * <p>
     * Se o valor procurado for NaN, retorna zero.
     * Para contar NaN, utilize {@link #contarNaN()}.
     *
     * @param valor      valor procurado
     * @param tolerancia tolerância absoluta, finita e não negativa
     * @return quantidade de elementos correspondentes
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  infinita ou NaN
     */
    public long contar(double valor, double tolerancia) {
        if (!Double.isFinite(tolerancia) || tolerancia < 0.0) {
            throw new IllegalArgumentException(
                    "A tolerância deve ser finita e não negativa.");
        }

        long quantidade = 0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double elemento = elementos[linha][coluna];

                if (elemento == valor
                        || (Double.isFinite(elemento)
                                && Double.isFinite(valor)
                                && Math.abs(elemento - valor) <= tolerancia)) {
                    quantidade++;
                }
            }
        }

        return quantidade;
    }

    /**
     * Conta os elementos que representam NaN.
     *
     * @return quantidade de valores NaN
     */
    public long contarNaN() {
        long quantidade = 0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                if (Double.isNaN(elementos[linha][coluna])) {
                    quantidade++;
                }
            }
        }

        return quantidade;
    }

    /**
     * Conta os elementos infinitos, positivos ou negativos.
     *
     * @return quantidade total de valores infinitos
     */
    public long contarInfinitos() {
        long quantidade = 0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                if (Double.isInfinite(elementos[linha][coluna])) {
                    quantidade++;
                }
            }
        }

        return quantidade;
    }

    /**
     * Copia os elementos para uma matriz com uma única linha.
     *
     * <p>
     * O percurso ocorre linha por linha, da esquerda para a direita.
     * A matriz original não é alterada.
     *
     * @return nova matriz com uma linha e todos os elementos
     * @throws IllegalStateException se a quantidade de elementos
     *                               exceder o limite permitido para uma dimensão
     */
    public Matriz comoVetorLinha() {
        long quantidade = (long) linhas * colunas;

        if (quantidade > Integer.MAX_VALUE) {
            throw new IllegalStateException(
                    "A quantidade de elementos excede o limite de uma dimensão.");
        }

        Matriz resultado = new Matriz(1, (int) quantidade);
        int indice = 0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[0][indice++] = elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Copia os elementos para uma matriz com uma única coluna.
     *
     * <p>
     * O percurso da matriz original ocorre linha por linha,
     * da esquerda para a direita. A matriz original não é alterada.
     *
     * @return nova matriz com uma coluna e todos os elementos
     * @throws IllegalStateException se a quantidade de elementos
     *                               exceder o limite permitido para uma dimensão
     */
    public Matriz comoVetorColuna() {
        long quantidade = (long) linhas * colunas;

        if (quantidade > Integer.MAX_VALUE) {
            throw new IllegalStateException(
                    "A quantidade de elementos excede o limite de uma dimensão.");
        }

        Matriz resultado = new Matriz((int) quantidade, 1);
        int indice = 0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                resultado.elementos[indice++][0] = elementos[linha][coluna];
            }
        }

        return resultado;
    }

    /**
     * Extrai uma cópia de um bloco retangular da matriz.
     *
     * <p>
     * A posição informada corresponde ao canto superior esquerdo
     * do bloco. A matriz original não é alterada.
     *
     * @param linha   índice da linha inicial
     * @param coluna  índice da coluna inicial
     * @param altura  quantidade de linhas do bloco
     * @param largura quantidade de colunas do bloco
     * @return nova matriz contendo os elementos do bloco
     * @throws IllegalArgumentException  se altura ou largura
     *                                   forem menores ou iguais a zero
     * @throws IndexOutOfBoundsException se a posição inicial for inválida
     *                                   ou se o bloco ultrapassar os limites da
     *                                   matriz
     */
    public Matriz bloco(int linha, int coluna, int altura, int largura) {
        if (altura <= 0 || largura <= 0) {
            throw new IllegalArgumentException(
                    "A altura e a largura do bloco devem ser maiores que zero.");
        }

        validarIndices(linha, coluna);

        // A subtração evita overflow que poderia ocorrer ao somar os limites.
        if (altura > linhas - linha || largura > colunas - coluna) {
            throw new IndexOutOfBoundsException(
                    "O bloco ultrapassa os limites da matriz.");
        }

        Matriz resultado = new Matriz(altura, largura);

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                resultado.elementos[i][j] = elementos[linha + i][coluna + j];
            }
        }

        return resultado;
    }

    /**
     * Copia um bloco para dentro desta matriz.
     *
     * <p>
     * A posição informada corresponde ao canto superior esquerdo
     * do destino. Os elementos dessa região são substituídos,
     * sem modificar os elementos restantes.
     *
     * <p>
     * Todas as validações são realizadas antes da alteração.
     * Os valores são copiados, sem compartilhar o armazenamento do bloco.
     *
     * @param linha  índice da linha inicial do destino
     * @param coluna índice da coluna inicial do destino
     * @param bloco  matriz com os valores que serão inseridos
     * @throws NullPointerException      se o bloco for nulo
     * @throws IndexOutOfBoundsException se a posição inicial for inválida
     *                                   ou se o bloco ultrapassar os limites da
     *                                   matriz
     */
    public void inserirBloco(int linha, int coluna, Matriz bloco) {
        if (bloco == null) {
            throw new NullPointerException(
                    "O bloco a ser inserido não pode ser nulo.");
        }

        validarIndices(linha, coluna);

        if (bloco.linhas > linhas - linha
                || bloco.colunas > colunas - coluna) {
            throw new IndexOutOfBoundsException(
                    "O bloco ultrapassa os limites da matriz.");
        }

        // A própria matriz só cabe em si mesma na posição (0, 0).
        if (bloco == this) {
            return;
        }

        for (int i = 0; i < bloco.linhas; i++) {
            for (int j = 0; j < bloco.colunas; j++) {
                elementos[linha + i][coluna + j] = bloco.elementos[i][j];
            }
        }
    }

    /**
     * Verifica se a matriz é antissimétrica dentro da tolerância.
     *
     * <p>
     * Uma matriz antissimétrica é quadrada e satisfaz A transposta = -A.
     * A diagonal principal deve ser aproximadamente zero.
     *
     * @param tolerancia tolerância absoluta, finita e não negativa
     * @return true se a matriz for antissimétrica dentro da tolerância
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isAntissimetrica(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        if (linhas != colunas || !possuiSomenteValoresFinitos()) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            if (Math.abs(elementos[i][i]) > tolerancia) {
                return false;
            }

            for (int j = i + 1; j < colunas; j++) {
                if (Math.abs(elementos[i][j] + elementos[j][i]) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se a matriz é ortogonal dentro da tolerância.
     *
     * <p>
     * Uma matriz ortogonal é quadrada e satisfaz A transposta * A = I.
     * Suas colunas são ortonormais.
     *
     * @param tolerancia tolerância absoluta para cada elemento do produto
     * @return true se a matriz for ortogonal dentro da tolerância
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isOrtogonal(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        if (linhas != colunas || !possuiSomenteValoresFinitos()) {
            return false;
        }

        for (int i = 0; i < colunas; i++) {
            for (int j = i; j < colunas; j++) {
                double produto = 0.0;

                for (int k = 0; k < linhas; k++) {
                    produto += elementos[k][i] * elementos[k][j];
                }

                double esperado = i == j ? 1.0 : 0.0;

                if (!Double.isFinite(produto)
                        || Math.abs(produto - esperado) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se a matriz é idempotente dentro da tolerância.
     *
     * <p>
     * Uma matriz idempotente é quadrada e satisfaz A * A = A.
     *
     * @param tolerancia tolerância absoluta para cada elemento do resultado
     * @return true se a matriz for idempotente dentro da tolerância
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isIdempotente(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        if (linhas != colunas || !possuiSomenteValoresFinitos()) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double produto = 0.0;

                for (int k = 0; k < colunas; k++) {
                    produto += elementos[i][k] * elementos[k][j];
                }

                if (!Double.isFinite(produto)
                        || Math.abs(produto - elementos[i][j]) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se a matriz é involutiva dentro da tolerância.
     *
     * <p>
     * Uma matriz involutiva é quadrada e satisfaz A * A = I.
     * Portanto, ela é sua própria inversa.
     *
     * @param tolerancia tolerância absoluta para cada elemento do produto
     * @return true se a matriz for involutiva dentro da tolerância
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isInvolutiva(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        if (linhas != colunas || !possuiSomenteValoresFinitos()) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double produto = 0.0;

                for (int k = 0; k < colunas; k++) {
                    produto += elementos[i][k] * elementos[k][j];
                }

                double esperado = i == j ? 1.0 : 0.0;

                if (!Double.isFinite(produto)
                        || Math.abs(produto - esperado) > tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se a matriz quadrada é numericamente singular.
     *
     * <p>
     * Utiliza eliminação de Gauss com pivotamento parcial em uma cópia.
     * Um pivô com valor absoluto menor ou igual à tolerância é
     * considerado numericamente zero.
     *
     * <p>
     * O resultado depende da tolerância e da escala dos valores.
     * Matrizes retangulares ou com elementos não finitos retornam false.
     *
     * @param tolerancia tolerância absoluta aplicada aos pivôs
     * @return true se algum pivô for considerado numericamente zero
     * @throws IllegalArgumentException se a tolerância for inválida
     * @throws ArithmeticException      se ocorrer resultado não finito
     *                                  durante a eliminação
     */
    public boolean isSingular(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        if (linhas != colunas || !possuiSomenteValoresFinitos()) {
            return false;
        }

        double[][] trabalho = new double[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            System.arraycopy(elementos[i], 0, trabalho[i], 0, colunas);
        }

        for (int k = 0; k < linhas; k++) {
            int linhaPivo = k;

            for (int i = k + 1; i < linhas; i++) {
                if (Math.abs(trabalho[i][k]) > Math.abs(trabalho[linhaPivo][k])) {
                    linhaPivo = i;
                }
            }

            if (Math.abs(trabalho[linhaPivo][k]) <= tolerancia) {
                return true;
            }

            if (linhaPivo != k) {
                double[] temporaria = trabalho[k];
                trabalho[k] = trabalho[linhaPivo];
                trabalho[linhaPivo] = temporaria;
            }

            for (int i = k + 1; i < linhas; i++) {
                double fator = trabalho[i][k] / trabalho[k][k];
                trabalho[i][k] = 0.0;

                for (int j = k + 1; j < colunas; j++) {
                    trabalho[i][j] -= fator * trabalho[k][j];

                    if (!Double.isFinite(trabalho[i][j])) {
                        throw new ArithmeticException(
                                "Resultado não finito durante "
                                        + "a verificação de singularidade.");
                    }
                }
            }
        }

        return false;
    }

    /**
     * Verifica se todos os elementos são estritamente positivos,
     * considerando a tolerância como limite inferior.
     *
     * <p>
     * Esta propriedade se refere aos elementos individualmente.
     * Ela é diferente de uma matriz ser definida positiva.
     *
     * @param tolerancia limite inferior finito e não negativo
     * @return true se todos os elementos forem finitos e maiores
     *         que a tolerância
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isPositiva(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double valor = elementos[i][j];

                if (!Double.isFinite(valor) || valor <= tolerancia) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se a matriz é simétrica definida positiva,
     * segundo os critérios numéricos adotados.
     *
     * <p>
     * Uma matriz real simétrica é definida positiva quando
     * x transposta * A * x é positivo para todo vetor x não nulo.
     *
     * <p>
     * A simetria é verificada com tolerância absoluta.
     * Em seguida, tenta construir um fator triangular inferior L
     * tal que A = L * L transposta, usando o algoritmo de Cholesky.
     * Cada pivô, antes da raiz quadrada, deve superar a tolerância.
     *
     * <p>
     * Quando há pequenas diferenças entre posições simétricas,
     * utiliza a média dessas posições para realizar o teste.
     * A matriz original não é alterada.
     *
     * @param tolerancia tolerância absoluta para simetria e pivôs
     * @return true se a matriz satisfizer os critérios do teste
     * @throws IllegalArgumentException se a tolerância for inválida
     * @throws ArithmeticException      se ocorrer resultado não finito
     *                                  durante o cálculo
     */
    public boolean isDefinidaPositiva(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        if (linhas != colunas || !possuiSomenteValoresFinitos()) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = i + 1; j < colunas; j++) {
                if (Math.abs(elementos[i][j] - elementos[j][i]) > tolerancia) {
                    return false;
                }
            }
        }

        double[][] inferior = new double[linhas][linhas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j <= i; j++) {
                // Usa a parte simétrica da matriz aceita pela tolerância.
                double valor = elementos[i][j];

                if (i != j && valor != elementos[j][i]) {
                    double outro = elementos[j][i];

                    if (Math.signum(valor) == Math.signum(outro)) {
                        valor += (outro - valor) / 2.0;
                    } else {
                        valor = (valor + outro) / 2.0;
                    }
                }

                for (int k = 0; k < j; k++) {
                    valor -= inferior[i][k] * inferior[j][k];
                }

                if (!Double.isFinite(valor)) {
                    throw new ArithmeticException(
                            "Resultado não finito durante "
                                    + "o teste de definição positiva.");
                }

                if (i == j) {
                    if (valor <= tolerancia) {
                        return false;
                    }

                    inferior[i][j] = Math.sqrt(valor);
                } else {
                    inferior[i][j] = valor / inferior[j][j];

                    if (!Double.isFinite(inferior[i][j])) {
                        throw new ArithmeticException(
                                "Resultado não finito durante "
                                        + "o teste de definição positiva.");
                    }
                }
            }
        }

        return true;
    }

    /**
     * Verifica se os elementos possuem valores aproximadamente iguais.
     *
     * <p>
     * A diferença entre o maior e o menor elemento deve ser
     * menor ou igual à tolerância. Aceita matrizes retangulares.
     *
     * @param tolerancia diferença absoluta máxima permitida
     * @return true se todos os elementos forem finitos e a amplitude
     *         estiver dentro da tolerância
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isConstante(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        double menor = elementos[0][0];
        double maior = elementos[0][0];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double valor = elementos[i][j];

                if (!Double.isFinite(valor)) {
                    return false;
                }

                menor = Math.min(menor, valor);
                maior = Math.max(maior, valor);
            }
        }

        return maior - menor <= tolerancia;
    }

    /**
     * Verifica se a matriz é esparsa conforme a convenção da biblioteca.
     *
     * <p>
     * Considera a matriz esparsa quando pelo menos metade dos elementos
     * possui valor absoluto menor ou igual à tolerância.
     * Não existe um percentual universal que defina esparsidade.
     *
     * <p>
     * Esta verificação não altera a representação interna da matriz
     * nem reduz seu consumo de memória.
     *
     * @param tolerancia tolerância absoluta para considerar um elemento zero
     * @return true se todos os elementos forem finitos e pelo menos
     *         metade for considerada zero
     * @throws IllegalArgumentException se a tolerância for inválida
     */
    public boolean isEsparsa(double tolerancia) {
        validarToleranciaPropriedade(tolerancia);

        long zeros = 0;
        long total = (long) linhas * colunas;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double valor = elementos[i][j];

                if (!Double.isFinite(valor)) {
                    return false;
                }

                if (Math.abs(valor) <= tolerancia) {
                    zeros++;
                }
            }
        }

        return zeros >= (total + 1) / 2;
    }

    /**
     * Valida a tolerância utilizada nas verificações de propriedades.
     *
     * @param tolerancia tolerância a verificar
     * @throws IllegalArgumentException se a tolerância for negativa,
     *                                  infinita ou NaN
     */
    private void validarToleranciaPropriedade(double tolerancia) {
        if (!Double.isFinite(tolerancia) || tolerancia < 0.0) {
            throw new IllegalArgumentException(
                    "A tolerância deve ser finita e não negativa.");
        }
    }

    /**
     * Verifica se todos os elementos são finitos.
     *
     * @return true se não houver NaN ou infinitos
     */
    private boolean possuiSomenteValoresFinitos() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!Double.isFinite(elementos[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Concatena esta matriz com outra horizontalmente.
     *
     * <p>
     * Esta matriz fica à esquerda e a outra à direita.
     * Ambas devem possuir a mesma quantidade de linhas.
     * As matrizes originais não são alteradas.
     *
     * @param outra matriz a ser adicionada à direita
     * @return nova matriz com as colunas das duas matrizes
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as quantidades de linhas
     *                                  forem diferentes ou se a quantidade
     *                                  resultante de colunas
     *                                  exceder o limite do tipo {@code int}
     */
    public Matriz concatenarHorizontalmente(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser concatenada não pode ser nula.");
        }

        if (linhas != outra.linhas) {
            throw new IllegalArgumentException(
                    "As matrizes devem ter a mesma quantidade de linhas "
                            + "para a concatenação horizontal.");
        }

        long totalColunas = (long) colunas + outra.colunas;

        if (totalColunas > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "A quantidade resultante de colunas excede "
                            + "o limite do tipo int.");
        }

        Matriz resultado = new Matriz(linhas, (int) totalColunas);

        for (int linha = 0; linha < linhas; linha++) {
            System.arraycopy(
                    elementos[linha], 0,
                    resultado.elementos[linha], 0,
                    colunas);

            System.arraycopy(
                    outra.elementos[linha], 0,
                    resultado.elementos[linha], colunas,
                    outra.colunas);
        }

        return resultado;
    }

    /**
     * Concatena esta matriz com outra verticalmente.
     *
     * <p>
     * Esta matriz fica acima e a outra abaixo.
     * Ambas devem possuir a mesma quantidade de colunas.
     * As matrizes originais não são alteradas.
     *
     * @param outra matriz a ser adicionada abaixo
     * @return nova matriz com as linhas das duas matrizes
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as quantidades de colunas
     *                                  forem diferentes ou se a quantidade
     *                                  resultante de linhas
     *                                  exceder o limite do tipo {@code int}
     */
    public Matriz concatenarVerticalmente(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser concatenada não pode ser nula.");
        }

        if (colunas != outra.colunas) {
            throw new IllegalArgumentException(
                    "As matrizes devem ter a mesma quantidade de colunas "
                            + "para a concatenação vertical.");
        }

        long totalLinhas = (long) linhas + outra.linhas;

        if (totalLinhas > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "A quantidade resultante de linhas excede "
                            + "o limite do tipo int.");
        }

        Matriz resultado = new Matriz((int) totalLinhas, colunas);

        for (int linha = 0; linha < linhas; linha++) {
            System.arraycopy(
                    elementos[linha], 0,
                    resultado.elementos[linha], 0,
                    colunas);
        }

        for (int linha = 0; linha < outra.linhas; linha++) {
            System.arraycopy(
                    outra.elementos[linha], 0,
                    resultado.elementos[linhas + linha], 0,
                    colunas);
        }

        return resultado;
    }

    /**
     * Concatena as matrizes formando dois blocos diagonais.
     *
     * <p>
     * Esta matriz ocupa o bloco superior esquerdo e a outra ocupa
     * o bloco inferior direito. Os demais elementos são zero.
     * Aceita matrizes retangulares com dimensões diferentes.
     *
     * <p>
     * A quantidade resultante de linhas é a soma das quantidades
     * de linhas; o mesmo vale para as colunas.
     * As matrizes originais não são alteradas.
     *
     * @param outra matriz que ocupará o bloco inferior direito
     * @return nova matriz com os dois blocos diagonais
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se alguma dimensão resultante
     *                                  exceder o limite do tipo {@code int}
     */
    public Matriz concatenarDiagonalmente(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz a ser concatenada não pode ser nula.");
        }

        long totalLinhas = (long) linhas + outra.linhas;
        long totalColunas = (long) colunas + outra.colunas;

        if (totalLinhas > Integer.MAX_VALUE
                || totalColunas > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "As dimensões resultantes excedem o limite do tipo int.");
        }

        Matriz resultado = new Matriz(
                (int) totalLinhas, (int) totalColunas);

        // Copia esta matriz para o bloco superior esquerdo.
        for (int linha = 0; linha < linhas; linha++) {
            System.arraycopy(
                    elementos[linha], 0,
                    resultado.elementos[linha], 0,
                    colunas);
        }

        // Copia a outra matriz para o bloco inferior direito.
        for (int linha = 0; linha < outra.linhas; linha++) {
            System.arraycopy(
                    outra.elementos[linha], 0,
                    resultado.elementos[linhas + linha], colunas,
                    outra.colunas);
        }

        return resultado;
    }

    /**
     * Retorna uma forma escalonada da matriz.
     *
     * <p>
     * Utiliza eliminação de Gauss com pivotamento parcial.
     * Os pivôs não são normalizados para um, e os elementos
     * acima dos pivôs não são eliminados.
     *
     * <p>
     * Usa uma tolerância relativa interna de 1e-12, baseada
     * no maior valor absoluto da matriz original. Valores pequenos
     * nessa escala podem ser considerados numericamente zero.
     *
     * <p>
     * Aceita matrizes retangulares e não altera a matriz original.
     *
     * @return nova matriz em forma escalonada numérica
     * @throws IllegalArgumentException se houver NaN ou infinito
     * @throws ArithmeticException      se ocorrer resultado não finito
     *                                  durante a eliminação
     */
    public Matriz escalonar() {
        double escala = 0.0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double valor = elementos[i][j];

                if (!Double.isFinite(valor)) {
                    throw new IllegalArgumentException(
                            "O escalonamento exige elementos finitos.");
                }

                escala = Math.max(escala, Math.abs(valor));
            }
        }

        Matriz resultado = copiar();

        if (escala == 0.0) {
            return resultado;
        }

        final double toleranciaRelativa = 1e-12;
        int linhaPivo = 0;

        for (int coluna = 0; coluna < colunas && linhaPivo < linhas; coluna++) {

            // Escolhe o maior candidato em valor absoluto na coluna.
            int melhorLinha = linhaPivo;
            double maior = Math.abs(
                    resultado.elementos[linhaPivo][coluna]);

            for (int i = linhaPivo + 1; i < linhas; i++) {
                double candidato = Math.abs(
                        resultado.elementos[i][coluna]);

                if (candidato > maior) {
                    maior = candidato;
                    melhorLinha = i;
                }
            }

            // A divisão evita calcular uma tolerância absoluta
            // que poderia sofrer underflow em matrizes muito pequenas.
            if (maior / escala <= toleranciaRelativa) {
                for (int i = linhaPivo; i < linhas; i++) {
                    resultado.elementos[i][coluna] = 0.0;
                }

                continue;
            }

            if (melhorLinha != linhaPivo) {
                double[] temporaria = resultado.elementos[linhaPivo];
                resultado.elementos[linhaPivo] = resultado.elementos[melhorLinha];
                resultado.elementos[melhorLinha] = temporaria;
            }

            double pivo = resultado.elementos[linhaPivo][coluna];

            for (int i = linhaPivo + 1; i < linhas; i++) {
                double fator = resultado.elementos[i][coluna] / pivo;
                resultado.elementos[i][coluna] = 0.0;

                for (int j = coluna + 1; j < colunas; j++) {
                    double valor = resultado.elementos[i][j]
                            - fator * resultado.elementos[linhaPivo][j];

                    if (!Double.isFinite(valor)) {
                        throw new ArithmeticException(
                                "Resultado não finito durante o escalonamento.");
                    }

                    resultado.elementos[i][j] = valor;
                }
            }

            linhaPivo++;
        }

        return resultado;
    }

    /**
     * Calcula a norma de Frobenius da matriz.
     *
     * <p>
     * Corresponde à raiz quadrada da soma dos quadrados
     * de todos os elementos.
     *
     * <p>
     * Utiliza Math.hypot para evitar overflow e underflow
     * desnecessários ao elevar os elementos ao quadrado.
     *
     * @return norma de Frobenius; NaN se houver algum NaN;
     *         infinito se houver infinito e nenhum NaN
     */
    public double normaFrobenius() {
        double norma = 0.0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double valor = elementos[i][j];

                if (Double.isNaN(valor)) {
                    return Double.NaN;
                }

                norma = Math.hypot(norma, valor);
            }
        }

        return norma;
    }

    /**
     * Calcula a norma matricial um.
     *
     * <p>
     * É a maior soma dos valores absolutos de uma coluna.
     *
     * @return norma um, ou NaN se houver algum NaN
     */
    public double normaUm() {
        double norma = 0.0;

        for (int coluna = 0; coluna < colunas; coluna++) {
            double soma = 0.0;

            for (int linha = 0; linha < linhas; linha++) {
                soma += Math.abs(elementos[linha][coluna]);
            }

            norma = Math.max(norma, soma);
        }

        return norma;
    }

    /**
     * Calcula a norma matricial infinito.
     *
     * <p>
     * É a maior soma dos valores absolutos de uma linha.
     *
     * @return norma infinito, ou NaN se houver algum NaN
     */
    public double normaInfinito() {
        double norma = 0.0;

        for (int linha = 0; linha < linhas; linha++) {
            double soma = 0.0;

            for (int coluna = 0; coluna < colunas; coluna++) {
                soma += Math.abs(elementos[linha][coluna]);
            }

            norma = Math.max(norma, soma);
        }

        return norma;
    }

    /**
     * Retorna o maior valor absoluto entre os elementos.
     *
     * <p>
     * Esta norma considera cada elemento individualmente,
     * sem calcular somas por linha ou coluna.
     *
     * @return maior valor absoluto, ou NaN se houver algum NaN
     */
    public double normaMaxima() {
        double norma = 0.0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                norma = Math.max(
                        norma, Math.abs(elementos[linha][coluna]));
            }
        }

        return norma;
    }

    /**
     * Calcula a distância de Frobenius entre duas matrizes.
     *
     * <p>
     * Corresponde à raiz quadrada da soma dos quadrados
     * das diferenças entre elementos correspondentes.
     * As matrizes originais não são alteradas.
     *
     * <p>
     * Diferenças indefinidas, como infinito menos infinito,
     * produzem NaN.
     *
     * @param outra matriz a ser comparada
     * @return distância entre as matrizes
     * @throws NullPointerException     se a matriz informada for nula
     * @throws IllegalArgumentException se as dimensões forem diferentes
     */
    public double distancia(Matriz outra) {
        if (outra == null) {
            throw new NullPointerException(
                    "A matriz para o cálculo da distância não pode ser nula.");
        }

        if (linhas != outra.linhas || colunas != outra.colunas) {
            throw new IllegalArgumentException(
                    "As matrizes devem ter as mesmas dimensões "
                            + "para o cálculo da distância.");
        }

        double resultado = 0.0;

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                double diferenca = elementos[linha][coluna]
                        - outra.elementos[linha][coluna];

                if (Double.isNaN(diferenca)) {
                    return Double.NaN;
                }

                resultado = Math.hypot(resultado, diferenca);
            }
        }

        return resultado;
    }

    /**
     * Calcula o erro absoluto global em relação à matriz de referência.
     *
     * <p>
     * Adota a norma de Frobenius da diferença como medida de erro,
     * produzindo o mesmo resultado de {@link #distancia(Matriz)}.
     *
     * @param outra matriz de referência
     * @return erro absoluto global
     * @throws NullPointerException     se a referência for nula
     * @throws IllegalArgumentException se as dimensões forem diferentes
     */
    public double erroAbsoluto(Matriz outra) {
        return distancia(outra);
    }

    /**
     * Calcula o erro relativo global em relação à matriz de referência.
     *
     * <p>
     * Divide o erro absoluto pela norma de Frobenius da referência.
     * O resultado é uma razão: por exemplo, 0.01 representa 1%.
     *
     * <p>
     * Se a norma da referência for zero, retorna zero quando
     * o erro absoluto também for zero; caso contrário, retorna
     * infinito positivo. Se o erro for NaN, retorna NaN.
     *
     * <p>
     * Resultados não finitos seguem as regras aritméticas de double.
     *
     * @param outra matriz de referência
     * @return erro relativo global
     * @throws NullPointerException     se a referência for nula
     * @throws IllegalArgumentException se as dimensões forem diferentes
     */
    public double erroRelativo(Matriz outra) {
        double erro = erroAbsoluto(outra);

        if (Double.isNaN(erro)) {
            return Double.NaN;
        }

        double normaReferencia = outra.normaFrobenius();

        if (normaReferencia == 0.0) {
            return erro == 0.0 ? 0.0 : Double.POSITIVE_INFINITY;
        }

        return erro / normaReferencia;
    }

    /**
     * Extrai uma região retangular da matriz.
     *
     * <p>
     * Os índices iniciais são inclusivos e os finais são exclusivos.
     * A região deve conter pelo menos uma linha e uma coluna.
     *
     * @param linhaInicio  primeira linha incluída
     * @param linhaFim     limite exclusivo das linhas
     * @param colunaInicio primeira coluna incluída
     * @param colunaFim    limite exclusivo das colunas
     * @return cópia independente da região
     * @throws IndexOutOfBoundsException se algum limite estiver
     *                                   fora das dimensões da matriz
     * @throws IllegalArgumentException  se os intervalos forem vazios
     *                                   ou estiverem invertidos
     */
    public Matriz submatriz(
            int linhaInicio, int linhaFim,
            int colunaInicio, int colunaFim) {

        if (linhaInicio < 0 || linhaInicio > linhas
                || linhaFim < 0 || linhaFim > linhas
                || colunaInicio < 0 || colunaInicio > colunas
                || colunaFim < 0 || colunaFim > colunas) {
            throw new IndexOutOfBoundsException(
                    "Os limites da submatriz devem estar dentro da matriz.");
        }

        if (linhaInicio >= linhaFim || colunaInicio >= colunaFim) {
            throw new IllegalArgumentException(
                    "Os intervalos devem ser crescentes e não vazios.");
        }

        return bloco(
                linhaInicio,
                colunaInicio,
                linhaFim - linhaInicio,
                colunaFim - colunaInicio);
    }

    /**
     * Calcula o menor complementar de uma posição.
     *
     * <p>
     * É o determinante da matriz obtida removendo a linha
     * e a coluna informadas.
     *
     * <p>
     * Para uma matriz 1 por 1, retorna 1, conforme a convenção
     * do determinante da matriz vazia.
     *
     * @param linha  índice da linha removida
     * @param coluna índice da coluna removida
     * @return menor complementar
     * @throws IllegalArgumentException  se a matriz não for quadrada
     * @throws IndexOutOfBoundsException se algum índice for inválido
     */
    public double menorComplementar(int linha, int coluna) {
        if (linhas != colunas) {
            throw new IllegalArgumentException(
                    "O menor complementar exige uma matriz quadrada.");
        }

        validarIndices(linha, coluna);

        if (linhas == 1) {
            return 1.0;
        }

        Matriz menor = new Matriz(linhas - 1, colunas - 1);
        int linhaDestino = 0;

        for (int i = 0; i < linhas; i++) {
            if (i == linha) {
                continue;
            }

            int colunaDestino = 0;

            for (int j = 0; j < colunas; j++) {
                if (j != coluna) {
                    menor.elementos[linhaDestino][colunaDestino++] = elementos[i][j];
                }
            }

            linhaDestino++;
        }

        return menor.determinante();
    }

    /**
     * Calcula o cofator de uma posição.
     *
     * <p>
     * O cofator é o menor complementar multiplicado por
     * (-1) elevado à soma dos índices.
     *
     * @param linha  índice da linha
     * @param coluna índice da coluna
     * @return cofator da posição
     * @throws IllegalArgumentException  se a matriz não for quadrada
     * @throws IndexOutOfBoundsException se algum índice for inválido
     */
    public double cofator(int linha, int coluna) {
        double menor = menorComplementar(linha, coluna);
        return linha % 2 == coluna % 2 ? menor : -menor;
    }

    /**
     * Calcula a matriz dos cofatores.
     *
     * @return nova matriz com o cofator de cada posição
     * @throws IllegalArgumentException se a matriz não for quadrada
     */
    public Matriz matrizDosCofatores() {
        if (linhas != colunas) {
            throw new IllegalArgumentException(
                    "A matriz dos cofatores exige uma matriz quadrada.");
        }

        Matriz resultado = new Matriz(linhas, colunas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado.elementos[i][j] = cofator(i, j);
            }
        }

        return resultado;
    }

    /**
     * Calcula a adjunta clássica, também chamada de adjugada.
     *
     * <p>
     * É a transposta da matriz dos cofatores.
     * Pode ser calculada mesmo quando a matriz é singular.
     * Não corresponde à operação de transposta conjugada.
     *
     * @return nova matriz com a adjugada
     * @throws IllegalArgumentException se a matriz não for quadrada
     */
    public Matriz adjunta() {
        return matrizDosCofatores().transposta();
    }

    /**
     * Calcula o posto numérico da matriz.
     *
     * <p>
     * Conta os pivôs obtidos por eliminação com pivotamento parcial.
     * Usa tolerância relativa de 1e-12 em relação ao maior valor
     * absoluto da matriz original.
     *
     * @return quantidade de pivôs numericamente não nulos
     * @throws IllegalArgumentException se houver elementos não finitos
     * @throws ArithmeticException      se ocorrer resultado não finito
     */
    public int posto() {
        int[] pivos = new int[Math.min(linhas, colunas)];
        return reduzirParaEspacos(pivos).posto;
    }

    /**
     * Calcula uma base do núcleo da matriz.
     *
     * <p>
     * O núcleo contém os vetores x que satisfazem A * x = 0.
     * Cada variável livre origina um vetor da base.
     * O espaço ambiente possui dimensão igual ao número de colunas.
     *
     * <p>
     * Se o núcleo contiver apenas o vetor zero, sua base será vazia
     * e sua dimensão será zero.
     *
     * @return espaço vetorial com uma base numérica do núcleo
     * @throws IllegalArgumentException se houver elementos não finitos
     * @throws ArithmeticException      se ocorrer resultado não finito
     */
    public EspacoVetorial nucleo() {
        int[] pivos = new int[Math.min(linhas, colunas)];
        ResultadoReducao reducao = reduzirParaEspacos(pivos);

        boolean[] colunaPivo = new boolean[colunas];

        for (int i = 0; i < reducao.posto; i++) {
            colunaPivo[pivos[i]] = true;
        }

        // Cada linha deste array armazena um vetor completo da base.
        double[][] vetores = new double[colunas - reducao.posto][colunas];
        int indiceVetor = 0;

        for (int livre = 0; livre < colunas; livre++) {
            if (colunaPivo[livre]) {
                continue;
            }

            double[] vetor = vetores[indiceVetor++];
            vetor[livre] = 1.0;

            for (int i = 0; i < reducao.posto; i++) {
                vetor[pivos[i]] = -reducao.dados[i][livre];
            }
        }

        return new EspacoVetorial(colunas, vetores);
    }

    /**
     * Calcula uma base da imagem da matriz.
     *
     * <p>
     * A imagem é o espaço gerado pelas colunas da matriz.
     * Seleciona as colunas originais correspondentes aos pivôs,
     * preservando seus valores.
     *
     * <p>
     * O espaço ambiente possui dimensão igual ao número de linhas.
     * Para uma matriz numericamente nula, a base é vazia.
     *
     * @return espaço vetorial com uma base numérica da imagem
     * @throws IllegalArgumentException se houver elementos não finitos
     * @throws ArithmeticException      se ocorrer resultado não finito
     */
    public EspacoVetorial imagem() {
        int[] pivos = new int[Math.min(linhas, colunas)];
        ResultadoReducao reducao = reduzirParaEspacos(pivos);

        double[][] vetores = new double[reducao.posto][linhas];

        for (int i = 0; i < reducao.posto; i++) {
            for (int linha = 0; linha < linhas; linha++) {
                vetores[i][linha] = elementos[linha][pivos[i]];
            }
        }

        return new EspacoVetorial(linhas, vetores);
    }

    /**
     * Armazena o resultado interno da redução.
     */
    private static final class ResultadoReducao {
        private final double[][] dados;
        private final int posto;

        private ResultadoReducao(double[][] dados, int posto) {
            this.dados = dados;
            this.posto = posto;
        }
    }

    /**
     * Produz uma forma escalonada reduzida para o cálculo de espaços.
     *
     * <p>
     * Normaliza inicialmente pela maior magnitude da matriz.
     * A normalização uniforme preserva o núcleo e as relações
     * de dependência linear.
     *
     * @param pivos array que receberá os índices das colunas pivô
     * @return dados reduzidos e posto numérico
     */
    private ResultadoReducao reduzirParaEspacos(int[] pivos) {
        double escala = 0.0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double valor = elementos[i][j];

                if (!Double.isFinite(valor)) {
                    throw new IllegalArgumentException(
                            "O cálculo exige elementos finitos.");
                }

                escala = Math.max(escala, Math.abs(valor));
            }
        }

        double[][] dados = new double[linhas][colunas];

        if (escala == 0.0) {
            return new ResultadoReducao(dados, 0);
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dados[i][j] = elementos[i][j] / escala;
            }
        }

        final double tolerancia = 1e-12;
        int posto = 0;

        for (int coluna = 0; coluna < colunas && posto < linhas; coluna++) {
            int melhorLinha = posto;

            for (int i = posto + 1; i < linhas; i++) {
                if (Math.abs(dados[i][coluna]) > Math.abs(dados[melhorLinha][coluna])) {
                    melhorLinha = i;
                }
            }

            if (Math.abs(dados[melhorLinha][coluna]) <= tolerancia) {
                for (int i = posto; i < linhas; i++) {
                    dados[i][coluna] = 0.0;
                }

                continue;
            }

            double[] temporaria = dados[posto];
            dados[posto] = dados[melhorLinha];
            dados[melhorLinha] = temporaria;

            double pivo = dados[posto][coluna];

            for (int j = coluna + 1; j < colunas; j++) {
                dados[posto][j] /= pivo;

                if (!Double.isFinite(dados[posto][j])) {
                    throw new ArithmeticException(
                            "Resultado não finito durante a redução.");
                }
            }

            dados[posto][coluna] = 1.0;

            // Elimina a coluna tanto abaixo quanto acima do pivô.
            for (int i = 0; i < linhas; i++) {
                if (i == posto) {
                    continue;
                }

                double fator = dados[i][coluna];

                for (int j = coluna + 1; j < colunas; j++) {
                    dados[i][j] -= fator * dados[posto][j];

                    if (!Double.isFinite(dados[i][j])) {
                        throw new ArithmeticException(
                                "Resultado não finito durante a redução.");
                    }
                }

                dados[i][coluna] = 0.0;
            }

            pivos[posto++] = coluna;
        }

        return new ResultadoReducao(dados, posto);
    }
}