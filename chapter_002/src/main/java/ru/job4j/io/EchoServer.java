package ru.job4j.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean proceed = true;
            while (proceed) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String message = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("GET /?msg=")) {
                            String[] reqData = str.split("\\s")[1]
                                    .split("=");
                            message = reqData.length > 1 ? reqData[1] : "";
                            if ("Exit".equals(message)) {
                                proceed = false;
                            }
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(message.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
