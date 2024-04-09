package com.example.codingbook;

public class PopularBookModel {
    String pid,pbook,pimage;

    public PopularBookModel() {
    }

    public PopularBookModel(String pid, String pname, String pimage) {
        this.pid = pid;
        this.pbook = pname;
        this.pimage = pimage;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPbook() {
        return pbook;
    }

    public void setPbook(String pbook) {
        this.pbook = pbook;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}
