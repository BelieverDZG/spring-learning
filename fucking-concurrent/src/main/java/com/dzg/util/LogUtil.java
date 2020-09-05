package com.dzg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil{

    public static Logger getLogger(Class<?> t){
        return LoggerFactory.getLogger(t);
    }
}
