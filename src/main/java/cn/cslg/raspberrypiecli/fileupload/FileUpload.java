package cn.cslg.raspberrypiecli.fileupload;

import cn.cslg.raspberrypiecli.socket.SocketManage;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * 用来上传文件
 * @author zeng
 */
public class FileUpload{
    SocketManage socketManage;
    OutputStream out;
    InputStream input;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    List<File> files;

    public FileUpload(SocketManage socketManage, List<File> files) {
        this.socketManage = socketManage;
        this.files = files;
    }

    public Boolean upload(List<File> files) throws Exception {

        input=socketManage.getInputStream();
        out=socketManage.getOutputStream();

        objectOutputStream=socketManage.getObjectOutputStream();
        objectInputStream=socketManage.getObjectInputStream();

        if (input==null||out==null||objectInputStream==null||objectOutputStream==null){
            return false;
        }

        //检查是否有文件不存在
        for (File file:files){
            if (!file.exists()){
                System.out.println(file.getName()+"不存在!");
                return false;
            }
        }

        //告知服务器，所有的文件信息
        objectOutputStream.writeObject(files);//告诉服务器，客户端需要发送的文件信息
        Boolean b=(Boolean) objectInputStream.readObject();//判断服务器是否准备接受
        if (!b){
            return false;
        }

        for (File file:files){
            try {
                InputStream inputStream = new FileInputStream(file);
                if (inputStream != null) {
                    byte[] buf = new byte[1024 * 10];
                    int len;
                    while ((len = inputStream.read(buf)) != -1) {
                        out.write(buf, 0, len);//不断写入服务器
                    }
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void start() {
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
