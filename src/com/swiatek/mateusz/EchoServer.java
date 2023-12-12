package com.swiatek.mateusz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int PORT = 2346;
    private ServerSocket socket;

    public EchoServer(){
        if(!createServerSocket()) System.exit(1);
        waitForClients();
    }
    private boolean createServerSocket(){
        try{
            socket = new ServerSocket(PORT);
            System.out.println("Utworzono socketa na porcie: " + PORT);
            return true;
        } catch (IOException e) {
            System.err.println("Nie udało się stworzyć na porcie: " + PORT + " error: " + e);
            return false;
        }
    }
    public void waitForClients(){
        System.out.println("Oczekiwanie na polaczenie...");
        int i = 0;
        try{
            while(true){
                Socket client = socket.accept();
                System.out.println("Zaakceptowano od: " + client.getInetAddress());
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                new NetCommunicator(client, in, out, i).start(); // Tworzenie i odrazu uruchamianie wątku.
                i++;
            }
        } catch(Exception e){
            System.err.println(e + ". Exiting...");
            System.exit(2);
        }

    }
}
