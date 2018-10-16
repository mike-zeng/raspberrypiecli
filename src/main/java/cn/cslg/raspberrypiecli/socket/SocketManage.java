package cn.cslg.raspberrypiecli.socket;

import cn.cslg.raspberrypiecli.bean.ClientInfo;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketManage {
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    ClientInfo clientInfo=null;

    public SocketManage(Socket socket, ClientInfo clientInfo) throws Exception{
        this.socket = socket;
        outputStream=socket.getOutputStream();
        inputStream=socket.getInputStream();
        objectOutputStream=new ObjectOutputStream(outputStream);
        objectInputStream=new ObjectInputStream(inputStream);
        this.clientInfo=clientInfo;
    }

    public Socket getSocket(){
        return socket;
    }
    public InputStream getInputStream() {
        return inputStream;
    }


    public OutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public Boolean login(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("id",clientInfo.getId());
            map.put("password",clientInfo.getPassword());
            objectOutputStream.writeObject(map);
            Boolean b=(Boolean) objectInputStream.readObject();
            return b;
        }catch (Exception e){
            return false;
        }
    }

    public void close(){
        try {
            if (objectOutputStream!=null){
                objectOutputStream.close();
            }

            if (objectInputStream!=null){
                objectInputStream.close();
            }

            if (inputStream!=null){
                inputStream.close();
            }

            if (outputStream!=null){
                outputStream.close();
            }

            if (socket!=null){
                socket.close();
            }
        }catch (Exception e){
            return;
        }
    }
}
