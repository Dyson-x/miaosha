package com.imocc.miaosha.domain;

/**
 * @author Dyson
 * @date 2019/7/30 18:23
 */
public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;
}
