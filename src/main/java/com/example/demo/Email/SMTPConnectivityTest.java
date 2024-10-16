package com.example.demo.Email;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SMTPConnectivityTest {
    public SMTPConnectivityTest() {
    }

    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        int port = 587;
        int timeout = 5000;

        try {
            Socket socket = new Socket();

            try {
                socket.connect(new InetSocketAddress(host, port), timeout);
                System.out.println("Connection to " + host + ":" + port + " successful.");
            } catch (Throwable var8) {
                try {
                    socket.close();
                } catch (Throwable var7) {
                    var8.addSuppressed(var7);
                }

                throw var8;
            }

            socket.close();
        } catch (IOException var9) {
            IOException e = var9;
            System.err.println("Failed to connect to " + host + ":" + port + ". Reason: " + e.getMessage());
        }

    }
}
