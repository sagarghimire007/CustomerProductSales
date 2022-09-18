package com.spring.customerproductsales.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@PathVariable String file){

//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getSize());
//        System.out.println(file.getContentType());
//        System.out.println(file.getName());
        return ResponseEntity.ok("working");
    }
}
