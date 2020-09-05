package org.dzg.beancreate.pojo;

/**
 * 创建一个实体类，作为操作的对象
 */
public class Customer {

    private Integer cid;
    private String cname;
    private String sex;

    public Customer() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
