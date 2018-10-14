package cn.cslg.raspberrypiecli.bean;

import java.io.Serializable;

public class ClientInfo implements Serializable {
    Integer id;
    String password;

    public ClientInfo() {
    }

    public ClientInfo(Integer id, String password) {
        this.id = id;
        this.password = password;
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

    @Override
    public String toString() {
        return "ClientInfo{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
