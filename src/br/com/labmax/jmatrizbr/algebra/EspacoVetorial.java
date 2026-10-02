package br.com.labmax.jmatrizbr.algebra;

import br.com.labmax.jmatrizbr.Matriz;

/**
 * Representa um subespaço real por uma base de vetores.
 *
 * <p>A dimensão do espaço é a quantidade de vetores da base.
 * A dimensão ambiente é a quantidade de componentes de cada vetor.
 *
 * <p>Uma base vazia representa o espaço que contém apenas o vetor zero.
 * Os dados são copiados na entrada e na saída.
 */
public final class EspacoVetorial {

    private final int dimensaoAmbiente;
    private final double[][] vetoresBase;

    /**
     * Cria um espaço a partir de uma base.
     *
     * <p>Cada array interno representa um vetor da base.
     * A independência é verificada pelo posto numérico da JMatrizBR.
     *
     * @param dimensaoAmbiente quantidade de componentes de cada vetor
     * @param vetoresBase vetores da base; pode ser um array vazio
     * @throws NullPointerException se o array ou algum vetor for nulo
     * @throws IllegalArgumentException se as dimensões forem inválidas,
     *         houver valores não finitos ou os vetores forem
     *         numericamente dependentes
     */
    public EspacoVetorial(int dimensaoAmbiente, double[][] vetoresBase) {
        if (dimensaoAmbiente <= 0) {
            throw new IllegalArgumentException(
                    "A dimensão ambiente deve ser maior que zero.");
        }

        if (vetoresBase == null) {
            throw new NullPointerException(
                    "O array de vetores da base não pode ser nulo.");
        }

        if (vetoresBase.length > dimensaoAmbiente) {
            throw new IllegalArgumentException(
                    "A base não pode ter mais vetores que a dimensão ambiente.");
        }

        this.dimensaoAmbiente = dimensaoAmbiente;
        this.vetoresBase = new double[vetoresBase.length][];

        for (int i = 0; i < vetoresBase.length; i++) {
            if (vetoresBase[i] == null) {
                throw new NullPointerException(
                        "O vetor da base no índice " + i + " é nulo.");
            }

            if (vetoresBase[i].length != dimensaoAmbiente) {
                throw new IllegalArgumentException(
                        "Todos os vetores devem ter "
                                + dimensaoAmbiente + " componentes.");
            }

            this.vetoresBase[i] = vetoresBase[i].clone();

            for (double valor : this.vetoresBase[i]) {
                if (!Double.isFinite(valor)) {
                    throw new IllegalArgumentException(
                            "Os vetores devem conter apenas valores finitos.");
                }
            }
        }

        if (this.vetoresBase.length > 0) {
            Matriz matrizBase = getBase();

            if (matrizBase.posto() != this.vetoresBase.length) {
                throw new IllegalArgumentException(
                        "Os vetores da base são numericamente dependentes "
                                + "na tolerância utilizada.");
            }
        }
    }

    /**
     * Retorna a dimensão do subespaço.
     *
     * @return quantidade de vetores da base
     */
    public int getDimensao() {
        return vetoresBase.length;
    }

    /**
     * Retorna a dimensão do espaço ambiente.
     *
     * @return quantidade de componentes de cada vetor
     */
    public int getDimensaoAmbiente() {
        return dimensaoAmbiente;
    }

    /**
     * Retorna uma cópia profunda dos vetores da base.
     *
     * @return array com um vetor por posição, ou vazio para dimensão zero
     */
    public double[][] getVetoresBase() {
        double[][] copia = new double[vetoresBase.length][];

        for (int i = 0; i < vetoresBase.length; i++) {
            copia[i] = vetoresBase[i].clone();
        }

        return copia;
    }

    /**
     * Retorna a base como uma matriz com um vetor por coluna.
     *
     * @return cópia da base em formato matricial
     * @throws IllegalStateException se a base for vazia, pois Matriz
     *         não aceita zero colunas; nesse caso use getVetoresBase()
     */
    public Matriz getBase() {
        if (vetoresBase.length == 0) {
            throw new IllegalStateException(
                    "A base é vazia. Use getVetoresBase() para consultá-la.");
        }

        Matriz base = new Matriz(dimensaoAmbiente, vetoresBase.length);

        for (int coluna = 0; coluna < vetoresBase.length; coluna++) {
            for (int linha = 0; linha < dimensaoAmbiente; linha++) {
                base.setElemento(linha, coluna, vetoresBase[coluna][linha]);
            }
        }

        return base;
    }

    /**
     * Retorna um vetor da base como matriz-coluna.
     *
     * @param indice índice do vetor, começando em zero
     * @return cópia independente do vetor
     * @throws IndexOutOfBoundsException se o índice for inválido
     */
    public Matriz getVetorBase(int indice) {
        if (indice < 0 || indice >= vetoresBase.length) {
            throw new IndexOutOfBoundsException(
                    "Índice de vetor inválido: " + indice);
        }

        Matriz vetor = new Matriz(dimensaoAmbiente, 1);

        for (int i = 0; i < dimensaoAmbiente; i++) {
            vetor.setElemento(i, 0, vetoresBase[indice][i]);
        }

        return vetor;
    }

    /**
     * Retorna a quantidade de vetores da base.
     *
     * @return dimensão do subespaço
     */
    public int quantidadeDeVetores() {
        return getDimensao();
    }

    @Override
    public String toString() {
        if (vetoresBase.length == 0) {
            return "Espaço zero em R^" + dimensaoAmbiente + "; base vazia.";
        }

        return "Dimensão: " + getDimensao()
                + "; ambiente: R^" + dimensaoAmbiente
                + System.lineSeparator() + getBase();
    }
}