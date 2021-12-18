package com.arnhomtestproj.Network;

import com.sun.tools.internal.xjc.ConsoleErrorReporter;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Vector;

public class Server {

    private int listeningPort;
    private Thread listeningThread;
    private ConnectionBuilder connectionBuilder;
    private List<Connection> connections = new Vector<>();

    public Server(int port, ConnectionBuilder connectionBuilder){
        listeningPort = port;
        this.connectionBuilder = connectionBuilder;
    }

    public void startListening(){
        Thread listeningThread = new Thread(() ->{
            while(true) {
                try {
                    ServerSocket serverSocket = new ServerSocket(listeningPort);
                    Socket clientSocket = serverSocket.accept();
                    Connection connection = connectionBuilder.build(clientSocket);
                    connections.add(connection);
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port "
                            + listeningPort + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        });
        listeningThread.start();
    }

    public void stopListening(){

    }


    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        System.out.println("listening on port: " + portNumber);

        try (
                ServerSocket serverSocket =
                        new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("got a connection");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // now get rid of extra end lines
                in.skip(1);
                System.out.println("received: " + inputLine);
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}




