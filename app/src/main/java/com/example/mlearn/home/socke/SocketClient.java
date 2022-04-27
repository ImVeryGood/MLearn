package com.example.mlearn.home.socke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端socket
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        // 链接服务端 如果链接成功 返回一个socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        // 链接上后 生成socket 通过 socket.getOupPutStream() 得到
        // 和socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        // 字节流 通过输出流 写入数据到管道
        outputStream.write("hello server".getBytes());
        // 结束标记
        socket.shutdownOutput();
        // 字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(" hello server 字符流");
        bufferedWriter.newLine();// 插入换行 标书写入结束
        bufferedWriter.flush(); //如果使用字符流 需要刷新
        /**
         * 通过socket。getInputStream() 读取客户端写入到数据通道的数据
         */
        InputStream inputStream = socket.getInputStream();
        // io 读取
        byte[] buf = new byte[1024];
        int readLn = 0;
        while ((readLn = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLn));
        }
        // 关闭对象和socket 必须关闭
        outputStream.close();
        socket.close();

    }
}
