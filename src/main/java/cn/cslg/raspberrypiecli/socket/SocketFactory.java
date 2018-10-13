package cn.cslg.raspberrypiecli.socket;

import cn.cslg.raspberrypiecli.bean.ServerBean;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 *
 */
public class SocketFactory {
    public static Socket getSocket(ServerBean serverBean){
        Socket socket=null;
        ObjectOutputStream outputStream=null;
        InputStream inputStream=null;
        try {
            socket=new Socket(serverBean.getIpAddress(),serverBean.getPort());
        }catch (Exception e){
            System.out.println(new Date()+" socket 获取失败!");
            return null;
        }finally {
            try {
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return socket;
    }
}
