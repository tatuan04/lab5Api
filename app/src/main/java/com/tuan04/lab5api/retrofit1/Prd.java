package com.tuan04.lab5api.retrofit1;

public class Prd {
    private String pid, name, price, desciption;

    public Prd() {
    }

    public Prd(String pid, String name, String price, String desciption) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.desciption = desciption;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
