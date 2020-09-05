package org.dzg.iocxml.domain;

import java.io.Serializable;

public class Account implements Serializable {

    private Integer id;
    private String aname;
    private float money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", aname='" + aname + '\'' +
                ", money=" + money +
                '}';
    }
}
