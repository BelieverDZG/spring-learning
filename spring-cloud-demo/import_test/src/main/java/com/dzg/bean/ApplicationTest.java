package com.dzg.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableUserBean
public class ApplicationTest {

    /**
     * ApplicationTest--> EnableUserBean --> UserImportSelector --> UserConfiguration --> User
     *
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationTest.class);
        User bean = ac.getBean(User.class);
        System.out.println(bean.toString());
    }
}
