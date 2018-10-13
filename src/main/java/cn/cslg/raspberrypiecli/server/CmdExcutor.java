package cn.cslg.raspberrypiecli.server;

import java.util.Date;

/**
 * 命令执行器
 * @author zeng
 */
public class CmdExcutor implements Runnable{
//    CmdBean cmdBean;
    String cmd;
    public CmdExcutor(String cmd){
        this.cmd=cmd;
    }
    public Boolean excute(){
        Runtime runtime=Runtime.getRuntime();
        try {
            runtime.exec(cmd);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void run() {
        if (cmd!=null){
            Boolean b=this.excute();
            if (b){
                System.out.println(new Date()+" "+cmd+"执行成功!");
            }else {
                System.out.println(new Date()+" "+cmd+"执行失败!");
            }
        }
    }
}
