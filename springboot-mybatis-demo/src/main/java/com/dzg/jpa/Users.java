package com.dzg.jpa;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键由数据库自动生成，Oracle不支持这种方式
    @Id
    private Integer id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;

    public Users() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "JPA {" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
