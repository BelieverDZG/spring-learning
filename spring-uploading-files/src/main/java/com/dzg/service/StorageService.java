package com.dzg.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 为Controller层提供交互的方法
 */
public interface StorageService {

    void init() throws Exception;

    void store(MultipartFile multipartFile);

    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName);

    void deleteAll();
}
