package com.dzg.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull //不允许为空
    @Size(min = 2,max = 10) //只允许名字长度为1-10个字符
    private String name;

    @NotNull
    @Min(18)
    private Integer age;

    public PersonForm(@NotNull @Size(min = 1, max = 70) String name, @NotNull @Min(18) Integer age) {
        this.name = name;
        this.age = age;
    }

    public PersonForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
