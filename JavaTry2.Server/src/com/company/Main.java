package com.company;

import common.DeterminantCalc;
import common.enums.Operation;
import common.models.RequestModel;
import common.models.MatrixModel;

import java.io.*;
import java.net.*;

public class Main {

    private final static int _socketPort = 7777;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientConnection = null;
        ObjectInputStream ois = null;
        ObjectOutputStream ous = null;

        try {
            System.out.println("Server is listening for a new connection...");
            serverSocket = new ServerSocket(_socketPort);

            clientConnection = serverSocket.accept();
            System.out.println("Connection established...");
            String clientIp = clientConnection.getRemoteSocketAddress().toString();

            ois = new ObjectInputStream(clientConnection.getInputStream());
            ous = new ObjectOutputStream(clientConnection.getOutputStream());

            RequestModel request = (RequestModel) ois.readObject();
            while(request.getOperation() != Operation.Exit)
            {
                System.out.println("new request from " + clientIp);

                String response = Double.toString(calculateDeterminant(request.getMatrix()));
                System.out.println("server response: '" + response + "' to the " + clientIp);
                ous.writeObject(response);

                request = (RequestModel) ois.readObject();
            }

            System.out.println(clientIp + " disconnected");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (ous != null) {
                    ous.close();
                }
                if (clientConnection != null) {
                    clientConnection.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static double calculateDeterminant(MatrixModel matrix) {
        DeterminantCalc calc = new DeterminantCalc();

        return calc.getDeterminant(matrix.getValues());
    }
}




