package cn.cslg.raspberrypiecli.server;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTask extends TimerTask {
    ObjectOutputStream outputStream=null;
    Timer timer=null;

    public TimeTask(ObjectOutputStream outputStream, Timer timer) {
     this.outputStream=outputStream;
     this.timer=timer;
    }

    public void run() {
        try {
            outputStream.writeObject(new Boolean(true));
        }catch (Exception e){
           timer.cancel();
        }
    }
}
