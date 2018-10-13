package cn.cslg.raspberrypiecli.bean;

public class FileBean {
    String filename;
    int filesize;

    public FileBean(String filename, int filesize) {
        this.filename = filename;
        this.filesize = filesize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }
}
