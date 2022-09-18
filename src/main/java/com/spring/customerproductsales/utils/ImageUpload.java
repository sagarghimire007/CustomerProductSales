package com.spring.customerproductsales.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {

    private final String UPLOAD_FOLDER = "C:\\Users\\sagar\\IdeaProjects\\CustomerProductSales\\src\\main\\resources\\static\\img\\product-image";

    boolean isUpload = false;
    public boolean uploadImage(MultipartFile imageProduct){
     try {
         Files.copy(imageProduct.getInputStream(),
                 Paths.get(UPLOAD_FOLDER + File.separator, imageProduct.getOriginalFilename()),
                 StandardCopyOption.REPLACE_EXISTING); //

         isUpload = true;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return isUpload;
    }


    // check whether the image is already existed or not
    public boolean checkExisted(MultipartFile imageProduct){
        boolean isExisted = false;
        try{
            File files = new File(UPLOAD_FOLDER + "\\" + imageProduct.getOriginalFilename());
            isExisted = files.exists();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExisted;
    }
}
