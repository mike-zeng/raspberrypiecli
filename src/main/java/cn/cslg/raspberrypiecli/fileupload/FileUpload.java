package cn.cslg.raspberrypiecli.fileupload;

import cn.cslg.raspberrypiecli.bean.ServerBean;
import cn.cslg.raspberrypiecli.socket.SocketFactory;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.List;

/**
 * 用来上传文件
 * @author zeng
 */
public class FileUpload implements Runnable{
    Socket socket;
    OutputStream out;
    InputStream input;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    List<File> files;

    public FileUpload(Socket socket, List<File> files) {
        this.socket = socket;
        this.files = files;
    }

    public Boolean upload(List<File> files) throws Exception {

        input=socket.getInputStream();
        out=socket.getOutputStream();

        objectOutputStream=new ObjectOutputStream(out);
        objectInputStream=new ObjectInputStream(input);

        if (input==null||out==null||objectInputStream==null||objectOutputStream==null){
            return false;
        }

        for (File file:files){
            if (!file.exists()) {
                System.out.println(Thread.currentThread().getName()+"      "+file.getName()+" 不存在");
                return false;
            }
            try {
                InputStream inputStream = new FileInputStream(file);
                if (inputStream != null) {

                    objectOutputStream.writeObject(file);//告诉服务器，客户端需要发送的文件信息

                    Boolean b=(Boolean) objectInputStream.readObject();//检查服务器是否接受上传

                    if (b){
                        byte[] buf = new byte[1024 * 10];
                        int len;
                        while ((len = inputStream.read(buf)) != -1) {
                            out.write(buf, 0, len);//不断写入服务器
                        }
                    }
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }finally {
                //回收资源
                objectOutputStream.close();
                objectInputStream.close();
                input.close();
                socket.close();
            }
        }
        return true;
    }

    public void run() {
        try {
            Boolean b=this.upload(files);
            if (b){
                System.out.println(new Date()+" "+"上传成功!");
            }else{
                System.out.println(new Date()+" "+"上传失败");
            }
        }catch (Exception e){
            System.out.println("文件上传失败!");
        }
    }
}
