package com.company;

import common.models.MatrixModel;
import common.models.RequestModel;

import java.io.*;
import java.net.*;

public class Main {
    private final static int _serverPort = 7777;
    private final static String _serverHost = "localhost";

    private final static String _newMatrixChoice = "1";
    private final static String _exitChoice = "2";

    public static void main(String[] args) {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket clientSocket = null;

        try {
            System.out.println("Trying to connect to " + _serverHost + ":" + _serverPort + "...");
            clientSocket = new Socket(_serverHost, _serverPort);
            System.out.println("Connection established...");

            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            String input;
            while (true) {
                input = showMainMenu(stdin);
                if(input.equals(_newMatrixChoice)) {
                    clearScreen();

                    System.out.println("Input matrix size: ");
                    do {
                        input = stdin.readLine();
                    } while (!isNumeric(input));

                    MatrixModel matrix = new MatrixModel(Integer.parseInt(input));
                    for (int i = 0; i < matrix.getSize(); i++) {
                        for (int j = 0; j < matrix.getSize(); j++) {
                            do {
                                System.out.println("Input value for [" + i + "][" + j + "]:");
                                input = stdin.readLine();
                            } while (!isNumeric(input));
                            matrix.setValue(i, j, Double.parseDouble(input));
                        }
                    }

                    printMatrix(matrix);

                    RequestModel request = new RequestModel(matrix);
                    oos.writeObject(request);

                    System.out.println("~Determinant~: " + ois.readObject());
                    System.out.println("~Press any key to return~");
                    stdin.read();
                }
                else if(input.equals(_exitChoice)) {
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printMatrix(MatrixModel matrix) {
        System.out.println("\n** Matrix **\n");
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                System.out.print(" " + matrix.getValue(i, j));
            }

            System.out.println();
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String showMainMenu(BufferedReader stdin) throws IOException {
        clearScreen();

        System.out.println("Welcome to the matrix determinant solver service");
        System.out.println("1. Find matrix determinant");
        System.out.println("2. Exit\n");

        return stdin.readLine();
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}

