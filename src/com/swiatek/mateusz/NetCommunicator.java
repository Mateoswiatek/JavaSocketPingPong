package com.swiatek.mateusz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetCommunicator extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int id;


    public NetCommunicator(Socket client, BufferedReader in, PrintWriter out, int id) {
        this.in = in;
        this.out = out;
        this.socket = client;
        this.id = id;
    }

    @Override
    public void run() {
        try{
            String msg = in.readLine(); // zablokuje się, dopóki nie otrzyma czegoś
            System.out.println("Received form " + socket.getInetAddress() + " #" + msg + "#");
            if(msg.equalsIgnoreCase("PING")){
                out.println("PONG");
                System.out.println("Send: PONG");
            } else {
                out.println("Wrong token, expected: PING");
            }
            out.flush(); // nie trzeba, bo jest automatyczny flush w konstruktorze, ale lepiej dać uniwersalnie.
            in.close();
            out.close();
            socket.close();
            System.out.println("spi watek: " + id );
            Thread.yield();
            Thread.sleep(5000);
            System.out.println("obudzil sie: " + id );
        } catch(Exception e){
            System.err.println(e + " Exiting");
        }
    }
}
