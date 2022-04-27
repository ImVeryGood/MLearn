package com.example.mlearn.home.socke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        /**
         *  本机的9999 端口 等待链接
         *  要求再本机没有其他服务监听9999
         *  多个accept 会返回多个socket
         */
        ServerSocket serverSocket = new ServerSocket(9999);
        /**
         * 当没有客户端链接9999端口时 程序会阻塞 等待链接
         * 如果客户端链接 则会返回socket 对象
         */
        Socket socket = serverSocket.accept();
        /**
         * 读取字节流 通过socket。getInputStream() 读取客户端写入到数据通道的数据
         */
        InputStream inputStream = socket.getInputStream();
        // io 读取
        byte[] buf = new byte[1024];
        int readLn = 0;
        while ((readLn = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLn));
        }
        /**
         * 字符流 读取
         */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);
        /**
         * 发送
         */
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello client".getBytes());
        // 结束标记
        socket.shutdownOutput();
        /**
         * 关闭流和socket
         */
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
