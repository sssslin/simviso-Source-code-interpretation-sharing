package com.simviso.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ZhiQiu
 * @email: fei6751803@163.com
 * @date: 2019/1/2 15:07.
 */
public class BIOServer {
    public void initBIOServer(int port)
    {
        ServerSocket serverSocket = null;//服务端Socket
        Socket socket = null;//客户端socket
        BufferedReader reader = null;
        String inputContent;
        int count = 0;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(stringNowTime() + ": serverSocket started");
            while(true)
            {
                socket = serverSocket.accept();
                System.out.println(stringNowTime() + ": id为" + socket.hashCode()+ "的Clientsocket connected");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((inputContent = reader.readLine()) != null) {
                    System.out.println("收到id为" + socket.hashCode() + "  "+inputContent);
                    count++;
                }
                System.out.println("id为" + socket.hashCode()+ "的Clientsocket "+stringNowTime()+"读取结束");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String stringNowTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static void main(String[] args) {
        BIOServer server = new BIOServer();
        server.initBIOServer(9898);

    }
}
