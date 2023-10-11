package org.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Array
{
    private int[][] matrix;

    public Array()
    {
        matrix = new int[3][3];
    }
    public void SetArray()
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter elements of new array:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
    }
    public int[][] GetArrray()
    {
        return matrix;
    }
    public void PrintArray(String str)
    {
        System.out.println(str+":");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // Переход на новую строку после каждой строки матрицы
        }
    }
    public void SendMatrixToClient( ObjectOutputStream coos )
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                try {
                    coos.writeObject(Integer.toString(matrix[i][j]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void GetMatrixFromClient( ObjectInputStream cois)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {

                try {
                    matrix[i][j]=Integer.parseInt((String)cois.readObject());
                   // matrix[i][j]=Integer.parseInt(clientMessageRecieved=(String)cois.readObject());
                    //System.out.println(cois.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void GetInverseMatrix()
    {
        this.matrix=MatrixInversion.GetInverseMatrix(matrix);
    }

}
