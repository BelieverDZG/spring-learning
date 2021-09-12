package com.dzg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created By 邓治国
 * 2020/5/13
 */
@Data
@RequiredArgsConstructor(staticName = "name")
@AllArgsConstructor
public class Nation {
    private Integer id;
    private String name;
}
