
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
- [x] 13. getDiagonalSecundaria()
- [x] 14. setLinha(int linha, double[] valores)
- [x] 15. setColuna(int coluna, double[] valores)
- [x] 16. preencher(double valor)
- [x] 17. preencherDiagonal(double valor)
- [x] 18. trocarLinhas(int linhaA, int linhaB)
- [x] 19. trocarColunas(int colunaA, int colunaB)
- [x] 20. validarDimensoes()
- [x] 21. somar(Matriz outra)
- [x] 22. subtrair(Matriz outra)
- [x] 23. multiplicarEscalar(double escalar)
- [x] 24. dividirEscalar(double divisor)
- [x] 25. multiplicar(Matriz outra)
- [x] 26. produtoHadamard(Matriz outra)
- [x] 27. produtoKronecker(Matriz outra)
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
- [x] 52. isAntissimetrica(double tolerancia)
- [x] 53. isOrtogonal(double tolerancia)
- [x] 54. isIdempotente(double tolerancia)
- [x] 55. isInvolutiva(double tolerancia)
- [x] 56. isSingular(double tolerancia)
- [x] 57. isPositiva(double tolerancia)
- [x] 58. isDefinidaPositiva(double tolerancia)
- [x] 59. isConstante(double tolerancia)
- [x] 60. isEsparsa(double tolerancia)
- [x] 61. determinante()
- [x] 62. inversa()
- [x] 63. submatriz(...)
- [x] 64. menorComplementar(int linha, int coluna)
- [x] 65. cofator(int linha, int coluna)
- [x] 66. matrizDosCofatores()
- [x] 67. adjunta()
- [x] 68. posto()
- [x] 69. nucleo()
- [x] 70. imagem()
- [ ] 71. resolverSistema(double[] termosIndependentes)
- [ ] 72. resolverSistema(Matriz termosIndependentes)
- [ ] 73. classificarSistema(double[] termosIndependentes)
- [ ] 74. verificarSolucao(double[] solucao, double[] termosIndependentes)
- [x] 75. escalonar()
- [x] 76. normaFrobenius()
- [x] 77. normaUm()
- [x] 78. normaInfinito()
- [x] 79. normaMaxima()
- [x] 80. distancia(Matriz outra)
- [x] 81. erroAbsoluto(Matriz outra)
- [x] 82. erroRelativo(Matriz outra)
- [x] 83. aproximadamenteIgual(...)
- [x] 84. somaTotal()
- [x] 85. media()
- [x] 86. mediana()
- [x] 87. variancia()
- [x] 88. desvioPadrao()
- [x] 89. minimo()
- [x] 90. maximo()
- [x] 91. somaPorLinha()
- [x] 92. somaPorColuna()
- [x] 93. mediaPorLinha()
- [x] 94. mediaPorColuna()
- [x] 95. minimoPorLinha()
- [x] 96. maximoPorLinha()
- [x] 97. minimoPorColuna()
- [x] 98. maximoPorColuna()
- [x] 99. contar(double valor, double tolerancia)
- [x] 100. contarNaN()
- [x] 101. contarInfinitos()
- [x] 102. comoVetorLinha()
- [x] 103. comoVetorColuna()
- [x] 104. bloco(int linha, int coluna, int altura, int largura)
- [x] 105. inserirBloco(int linha, int coluna, Matriz bloco)
- [x] 106. concatenarHorizontalmente(Matriz outra)
- [x] 107. concatenarVerticalmente(Matriz outra)
- [x] 108. concatenarDiagonalmente(Matriz outra)
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