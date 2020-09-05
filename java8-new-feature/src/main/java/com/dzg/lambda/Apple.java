package com.dzg.lambda;

import java.io.Serializable;

/**
 * 苹果实体类，作为lambda演练的实例
 */
public class Apple implements Serializable {

    private String color;
    private int weight;


    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
