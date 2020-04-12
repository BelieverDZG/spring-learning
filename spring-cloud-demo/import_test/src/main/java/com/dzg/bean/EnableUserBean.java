package com.dzg.bean;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(UserImportSelector.class)
public @interface EnableUserBean {
}
