package cn.cslg.raspberrypiecli.main;

import cn.cslg.raspberrypiecli.bean.ConfigBean;
import cn.cslg.raspberrypiecli.bean.ServerBean;
import cn.cslg.raspberrypiecli.fileupload.FileUpload;
import cn.cslg.raspberrypiecli.server.Server;
import cn.cslg.raspberrypiecli.socket.SocketFactory;

import java.io.File;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 主程序入口
 * @author zeng
 */
public class Main {
    public static void main(String args[]) {

        if (args.length <= 1) {
            System.out.println(new Date()+" 请输入参数");
            return;
        }
        String configfile= args[0];//配置文件

        //1.配置文件加载
        ConfigBean configBean=new ConfigBean();
        Boolean b=configBean.init(configfile);
        if (!b){
            System.out.println(new Date()+" 配置文件加载失败!或者配置文件参数错误");
            return;
        }

        //serverBean获取
        ServerBean serverBeanForUpLoad=new ServerBean(configBean.getIpAddress(),configBean.getPort_upload());
        ServerBean serverBeanForCmdServer=new ServerBean(configBean.getIpAddress(),configBean.getPort_cmd());


        //3.上传文件
        if ((args[1].equals("--upload") || args[1].equals("-u")) && args.length >= 3) {
            List<File> files=new LinkedList<File>();
            for (int i=2;i<args.length;i++){
                String filename = args[i];
                File file = new File(filename);
                files.add(file);
            }

            Socket socket=SocketFactory.getSocket(serverBeanForUpLoad);
            FileUpload fileUpload=new FileUpload(socket,files);
        }
        else if ((args[1].equals("--server") || args[1].equals("-s")) && args.length == 2) {
            //启动服务连接服务器，处理服务器发出的指令
            Socket socket= SocketFactory.getSocket(serverBeanForCmdServer);
            Server server=new Server(socket,configBean.getId());
            server.start();
        }
        else if (args[1].equals("--config")||args[1].equals("-c")&&args.length==2){
            configBean.showConfig();
        }
        else if (args[1].equals("--set")&&args.length>=4&&args.length%2==0){
            for(int i=1;i<args.length;i++){
                if (i%2==0){
                    b=configBean.set(configfile,args[i],args[i+1]);
                    if (b){
                        System.out.println(new Date()+args[i]+"设置成功!");
                    }
                }
            }
        }else if ((args[1].equals("--help")||args[1].equals("-h"))&&args.length==2){
            showHelp();
        }
        else {
            System.out.println(new Date()+" 输入参数错误");
        }

        System.out.println("程序结束!");
    }

    public static void showHelp(){
        System.out.println("                  命令参数                       ");
        System.out.println();
        System.out.println("1.          --config 或 -c               查看参数");
        System.out.println();
        System.out.println("2.          --upload 或 -u 【文件名】       上传文件");
        System.out.println();
        System.out.println("3.          --server 或 -s               启动服务");
        System.out.println();
        System.out.println("4.          --set  [参数] [值】            设置参数");
        System.out.println();
        System.out.println("5.          --help   或 -h               查看帮助");
    }
}
