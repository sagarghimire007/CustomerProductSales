package com.spring.customerproductsales;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@RestController
class CustomerProductSalesApplicationTests {

    @Test
    void contextLoads() {
    }

    public class FileUploadController {

        @PostMapping("/uploadFile")
        public ResponseEntity<String> uploadFile(@ModelAttribute("file") MultipartFile file) {

            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
            System.out.println(file.getContentType());
            System.out.println(file.getName());
            return ResponseEntity.ok("working");
        }
    }

}
