
# JMatrizBR

## Início

Biblioteca em Java SE para trabalhar com matrizes e documentação em português brasileiro.


## Funcionalidades em implantação:

As funcionalidades abaixo representam o planejamento
da biblioteca e serão implementadas gradualmente.

- [x] 01. Matriz(int linhas, int colunas)
- [x] 02. Matriz(double[][] valores)
- [x] 03. copiar()
- [x] 04. getLinhas()
- [x] 05. getColunas()
- [x] 06. getDimensoes()
- [x] 07. getElemento(int linha, int coluna)
- [x] 08. setElemento(int linha, int coluna, double valor)
- [x] 09. getLinha(int linha)
- [x] 10. getColuna(int coluna)
- [x] 11. getElementos()
- [x] 12. getDiagonalPrincipal()
- [ ] 13. getDiagonalSecundaria()
- [ ] 14. setLinha(int linha, double[] valores)
- [ ] 15. setColuna(int coluna, double[] valores)
- [ ] 16. preencher(double valor)
- [ ] 17. preencherDiagonal(double valor)
- [ ] 18. trocarLinhas(int linhaA, int linhaB)
- [ ] 19. trocarColunas(int colunaA, int colunaB)
- [ ] 20. validarDimensoes()
- [x] 21. somar(Matriz outra)
- [x] 22. subtrair(Matriz outra)
- [x] 23. multiplicarEscalar(double escalar)
- [x] 24. dividirEscalar(double divisor)
- [x] 25. multiplicar(Matriz outra)
- [ ] 26. produtoHadamard(Matriz outra)
- [ ] 27. produtoKronecker(Matriz outra)
- [x] 28. transposta()
- [x] 29. traco()
- [x] 30. potencia(int expoente)
- [ ] 31. comutador(Matriz outra)
- [ ] 32. somarDiagonal(double valor)
- [ ] 33. subtrairDiagonal(double valor)
- [ ] 34. diagonalPrincipalComoMatriz()
- [ ] 35. diagonalSecundariaComoMatriz()
- [ ] 36. parteEstritamenteSuperior()
- [ ] 37. parteEstritamenteInferior()
- [ ] 38. diagonaisParalelas()
- [ ] 39. reshape(int novasLinhas, int novasColunas)
- [ ] 40. achatar()
- [x] 41. Matriz.identidade(int ordem)
- [x] 42. Matriz.diagonal(double[] valores)
- [x] 43. triangularSuperior()
- [x] 44. triangularInferior()
- [x] 45. isQuadrada()
- [x] 46. isNula(double tolerancia)
- [x] 47. isIdentidade(double tolerancia)
- [x] 48. isSimetrica(double tolerancia)
- [x] 49. isDiagonal(double tolerancia)
- [x] 50. isTriangularSuperior(double tolerancia)
- [x] 51. isTriangularInferior(double tolerancia)
- [ ] 52. isAntissimetrica(double tolerancia)
- [ ] 53. isOrtogonal(double tolerancia)
- [ ] 54. isIdempotente(double tolerancia)
- [ ] 55. isInvolutiva(double tolerancia)
- [ ] 56. isSingular(double tolerancia)
- [ ] 57. isPositiva(double tolerancia)
- [ ] 58. isDefinidaPositiva(double tolerancia)
- [ ] 59. isConstante(double tolerancia)
- [ ] 60. isEsparsa(double tolerancia)
- [x] 61. determinante()
- [x] 62. inversa()
- [ ] 63. submatriz(...)
- [ ] 64. menorComplementar(int linha, int coluna)
- [ ] 65. cofator(int linha, int coluna)
- [ ] 66. matrizDosCofatores()
- [ ] 67. adjunta()
- [ ] 68. posto()
- [ ] 69. nucleo()
- [ ] 70. imagem()
- [ ] 71. resolverSistema(double[] termosIndependentes)
- [ ] 72. resolverSistema(Matriz termosIndependentes)
- [ ] 73. classificarSistema(double[] termosIndependentes)
- [ ] 74. verificarSolucao(double[] solucao, double[] termosIndependentes)
- [ ] 75. escalonar()
- [ ] 76. normaFrobenius()
- [ ] 77. normaUm()
- [ ] 78. normaInfinito()
- [ ] 79. normaMaxima()
- [ ] 80. distancia(Matriz outra)
- [ ] 81. erroAbsoluto(Matriz outra)
- [ ] 82. erroRelativo(Matriz outra)
- [x] 83. aproximadamenteIgual(...)
- [x] 84. somaTotal()
- [ ] 85. media()
- [ ] 86. mediana()
- [ ] 87. variancia()
- [ ] 88. desvioPadrao()
- [ ] 89. minimo()
- [ ] 90. maximo()
- [ ] 91. somaPorLinha()
- [ ] 92. somaPorColuna()
- [ ] 93. mediaPorLinha()
- [ ] 94. mediaPorColuna()
- [ ] 95. minimoPorLinha()
- [ ] 96. maximoPorLinha()
- [ ] 97. minimoPorColuna()
- [ ] 98. maximoPorColuna()
- [ ] 99. contar(double valor, double tolerancia)
- [ ] 100. contarNaN()
- [ ] 101. contarInfinitos()
- [ ] 102. comoVetorLinha()
- [ ] 103. comoVetorColuna()
- [ ] 104. bloco(int linha, int coluna, int altura, int largura)
- [ ] 105. inserirBloco(int linha, int coluna, Matriz bloco)
- [ ] 106. concatenarHorizontalmente(Matriz outra)
- [ ] 107. concatenarVerticalmente(Matriz outra)
- [ ] 108. concatenarDiagonalmente(Matriz outra)
- [x] 109. equals(Object objeto)
- [x] 110. hashCode()
- [x] 111. igualExata(Matriz outra)
- [x] 112. formatar(int casasDecimais)

## Estrutura atual

O código-fonte está na pasta `src`.

A classe principal da biblioteca é `Matriz`, localizada em:

`src/br/com/labmax/jmatrizbr/Matriz.java`

## Como obter o projeto

```bash
git clone https://github.com/albertfrancajosuacosta/JMatrizBR.git
cd JMatrizBR
```


## Contato

Albert França Josuá Costa

![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white) <albertfrancajosuacosta@gmail.com>

![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white) <https://www.linkedin.com/in/albert-josu%C3%A1-9aa550239/>




## Distintivos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)