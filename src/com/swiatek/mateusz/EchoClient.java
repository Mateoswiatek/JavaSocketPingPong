package com.swiatek.mateusz;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String... args){
        String host = "localhost"; // adres ip
        int port = EchoServer.PORT;
        if(args.length > 1){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        Socket socket;
        try{
            socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            out.println("PING");
            System.out.println("Sent: PING");
            String msg = in.readLine(); // tu sie blokuje, czeka na wiadomość zwrotną
            System.out.println("Received: #" + msg + "#");
//            out.flush(); // nie trzeba, bo jest autoFlush
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println(e + ". Exiting");
            System.exit(1);
        }
    }
}
