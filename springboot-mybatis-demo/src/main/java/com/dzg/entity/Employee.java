package com.dzg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Created By 邓治国
 * 2020/5/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    private String name;

    private String gender;

    private String idCard;

    private Integer nationId;

    private String nativePlace;

    private String phone;


    private String beginContract;

    private String endContract;

    private Integer workAge;

    private Nation nation;

}
