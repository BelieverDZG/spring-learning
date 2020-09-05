package com.dzg.mmentity.common;

import java.io.Serializable;

/**
 * 作为统一返回的bean对象
 * @param <T>
 */
public class R<T> implements Serializable {

    public static final long serialVersionUID = 1L;
    public static final int OK = 0;
    public static final int FAIL = 1;
    public static final int UNAUTHORIZED = 2;

    private T data;//服务端的数据
    private int status = OK;//状态码
    private String msg = "";//描述信息

    public static int getOK() {
        return OK;
    }

    public static int getFAIL() {
        return FAIL;
    }

    public static int getUNAUTHORIZED() {
        return UNAUTHORIZED;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //APIS
    public static R isOk(){
        return new R();
    }

    public static R isFail(){
        return new R().status(FAIL);
    }

    private R status(int fail) {
        this.setStatus(fail);
        return this;
    }

    public static R isFail(Throwable e){
        return isFail().msg(e);
    }

    private R msg(Throwable e) {
        this.setMsg(e.toString());
        return this;
    }

    public R data(T data){
        this.setData(data);
        return this;
    }

    public R() {
    }
}
