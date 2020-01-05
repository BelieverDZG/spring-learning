package com.dzg.pojo;

public class Submission {

    private String job;

    private Double salary;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Submission() {
    }

    public Submission(String job, Double salary) {
        this.job = job;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "job='" + job + '\'' +
                ", salary=" + salary +
                '}';
    }
}
