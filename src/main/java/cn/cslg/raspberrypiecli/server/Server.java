package cn.cslg.raspberrypiecli.server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 接受来自服务端的命令，并执行
 */
public class Server {
    private Socket socket;
    Timer timer=new Timer();

    public Server(Socket socket,int id){
        this.socket=socket;
    }

    public void start(){
        try {
            if (socket==null){
                System.out.println(new Date()+" 连接服务器失败!");
                return;
            }

            System.out.println(new Date()+" 已连接服务器!");
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());

            outputStream.writeObject(true);

            inputStream.readObject();

            if (inputStream==null||outputStream==null){
                System.out.println(new Date()+" 连接服务器失败!");
                return;
            }
            sentHeart(outputStream);
            while (true){
                String temp=(String) inputStream.readObject();
                if (temp!=null){
                    CmdExcutor cmdExcutor=new CmdExcutor(temp);//接受到服务端发送的指令，校验后执行.
                    Thread thread=new Thread(cmdExcutor);
                    thread.start();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //指定时间发送心跳包
    private void sentHeart(ObjectOutputStream outputStream){
        TimerTask timerTask=new TimeTask(outputStream,timer);
        timer.schedule(timerTask,0,1000*30);
    }

}
