package cn.cslg.raspberrypiecli.socket;

import cn.cslg.raspberrypiecli.bean.ServerBean;


import java.net.Socket;
import java.util.Date;

/**
 *
 */
public class SocketFactory {
    public static Socket getSocket(ServerBean serverBean){
        Socket socket=null;
        try {
            socket=new Socket(serverBean.getIpAddress(),serverBean.getPort());
        }catch (Exception e){
            System.out.println(new Date()+" socket 获取失败!");
            return null;
        }
        return socket;
    }
}
