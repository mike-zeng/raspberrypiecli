package cn.cslg.raspberrypiecli.bean;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 存放全局的配置信息
 */
public class ConfigBean {
    PropertiesConfiguration propertiesConfiguration=null;
    public  Integer id;
    public  String password;
    public  String ipAddress;
    public  Integer port_upload;
    public  Integer port_cmd;


    public ConfigBean() {
    }

    public Boolean init(String configfile){
        try {
            Properties properties=new Properties();
            properties.load(new FileInputStream(configfile));
            this.id=Integer.valueOf(properties.getProperty("id"));
            this.password=properties.getProperty("password");
            this.ipAddress=properties.getProperty("ipAddress");
            this.port_cmd=Integer.valueOf(properties.getProperty("port_cmd"));
            this.port_upload=Integer.valueOf(properties.getProperty("port_upload"));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean set(String configfile,String key,String value){
        OutputStream outputStream=null;
        try {

            Properties properties=new Properties();
            properties.load(new FileInputStream(configfile));

            outputStream=new FileOutputStream(configfile);
            if (properties.getProperty(key)!=null){
                properties.setProperty(key,value);
            }else {
                return false;
            }
            properties.store(outputStream,null);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.init(configfile);
        return true;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort_upload() {
        return port_upload;
    }

    public void setPort_upload(Integer port_upload) {
        this.port_upload = port_upload;
    }

    public Integer getPort_cmd() {
        return port_cmd;
    }

    public void setPort_cmd(Integer port_cmd) {
        this.port_cmd = port_cmd;
    }

    public void showConfig(){
        System.out.println("配置文件参数为:");
        System.out.println("id = "+id);
        System.out.println("password = "+password);
        System.out.println("ipAddress = "+ipAddress);
        System.out.println("port_upload = "+port_upload);
        System.out.println("port_cmd = "+port_cmd);
    }
    @Override
    public String toString() {
        return "ConfigBean{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port_upload=" + port_upload +
                ", port_cmd=" + port_cmd +
                '}';
    }
}
