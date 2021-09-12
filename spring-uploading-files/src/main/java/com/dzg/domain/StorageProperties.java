package com.dzg.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * folder location for storing files
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
