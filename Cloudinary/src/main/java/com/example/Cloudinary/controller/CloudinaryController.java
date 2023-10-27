package com.example.Cloudinary.controller;

import com.example.Cloudinary.entity.Cloud;
import com.example.Cloudinary.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public Map create(@RequestPart("image")MultipartFile multipartFile) throws IOException {
        return cloudinaryService.upload(multipartFile);
    }

    @PostMapping("/new")
    public Map newCreate(@RequestPart("photo") MultipartFile multipartFile, @RequestPart("details") Cloud cloud) throws IOException {
        return cloudinaryService.newUpload(multipartFile,cloud);
    }

}
