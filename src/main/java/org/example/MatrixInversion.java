package org.example;

public class MatrixInversion {

    public static int [][] GetInverseMatrix(int [][]matrix)
    {
            return MatrixInversion.invert(matrix);
    }



    private static int[][] invert(int[][] matrix) {
        int n = matrix.length;
        int[][] augmentedMatrix = new int[n][2 * n];
        int[][] inverseMatrix = new int[n][n];

        // Создаем расширенную матрицу, объединяя исходную матрицу с единичной матрицей
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
                augmentedMatrix[i][j + n] = (i == j) ? 1 : 0; // Единичная матрица
            }
        }

        // Проходим по каждой строке
        for (int i = 0; i < n; i++) {
            int pivot = augmentedMatrix[i][i];

            // Делаем текущий элемент диагонали равным 1
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            // Обнуляем все элементы в столбце, кроме диагонального элемента
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    int factor = augmentedMatrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                    }
                }
            }
        }

        // Копируем обратную матрицу из расширенной матрицы
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = augmentedMatrix[i][j + n];
            }
        }

        return inverseMatrix;
    }
}
