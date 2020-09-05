package com.dzg.controller;

import com.dzg.exception.StorageFileNotFoundException;
import com.dzg.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Spring MVC 会根据注解@Controller来查找路径
 */
@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 从StorageService上传的文件清单中查找文件，并将它加载到Thymeleaf模板中
     *
     * MvcUriComponentsBuilder ：将一个连接指向其实际的资源
     *
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/")
    public String listUploadFiles(Model model) throws IOException {

        Stream<Path> pathStream = storageService.loadAll();

        Function<Path, String> serveFile = path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString()).build().toString();

        Collector<String, ?, List<String>> stringListCollector = Collectors.toList();

        model.addAttribute("files", pathStream.map(
                serveFile
        ).collect(stringListCollector));

        return "fileUploadForm";
    }

    /**
     * 加载资源（如果存在的话）并将其发送至浏览器下载（设置请求响应头：CONTENT_DISPOSITION）
     * @param fileName
     * @return
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+file.getFilename()+"\"").body(file);
    }

    /**
     * 实现多个文件的上传
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        storageService.store(file);

        redirectAttributes.addFlashAttribute("message","You successfully upload "+file.getOriginalFilename()+"!");

        return "redirect:/";

    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc){
        return ResponseEntity.notFound().build();
    }


}
