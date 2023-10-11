package org.example;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    private static int[][] matrix = new int[3][3];
    public static void main(String[] arg)
    {//объявление объекта класса ServerSocket
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;//объявление объекта класса Socket
        ObjectInputStream  sois   = null;//объявление байтового потока ввода
        ObjectOutputStream soos   = null;//объявление байтового потока вывода
        try {
            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
            clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
            System.out.println("connection established....");
//создание потока ввода
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока
//вывода
            String clientMessageRecieved=null;//объявление //строки и присваивание ей данных потока ввода, представленных
            Array array=new Array();
            while(true)
            {
                array.GetMatrixFromClient(sois);
                array.PrintArray("Matrix");
                array.GetInverseMatrix();
                array.PrintArray("Inverse matrix");
                array.SendMatrixToClient(soos);
            }
        }catch(Exception e)  {
        } finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } catch(Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }
}
