package com.dzg.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class UserImportSelector implements ImportSelector {

    //获取配置类的名称
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{UserConfiguration.class.getName()};
    }
}
