package com.dzg.service.impl;

import com.dzg.domain.StorageProperties;
import com.dzg.exception.StorageException;
import com.dzg.exception.StorageFileNotFoundException;
import com.dzg.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage",e);
        }
    }

    @Override
    public void store(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            if (multipartFile.isEmpty()){
                throw new StorageException("Fail to store empty file "+fileName);
            }
            //安全检查
            if (fileName.contains("..")){
                throw new StorageException("Could not store with relative path outside current directory "+fileName);
            }

            try (InputStream inputStream = multipartFile.getInputStream()){
                Files.copy(inputStream,this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Fail to store file "+fileName,e);
        }
    }

    /**
     * 以流德形式加载所有的文件
     * @return
     */
    @Override
    public Stream<Path> loadAll() {
        try {
            Stream<Path> streamPath = Files.walk(this.rootLocation,1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
            return streamPath;
        } catch (IOException e) {
            throw new StorageException("Fail to read stored files",e);
        }

    }

    /**
     * 根据文件名加载文件
     * @param fileName
     * @return
     */
    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    /**
     * 根据文件名将相应的文件加载为资源
     * @param fileName
     * @return
     */
    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path path = load(fileName);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new StorageFileNotFoundException("Could not read file "+fileName);
            }
        } catch (MalformedURLException e) {
            throw  new StorageFileNotFoundException("Could not read file "+fileName,e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
