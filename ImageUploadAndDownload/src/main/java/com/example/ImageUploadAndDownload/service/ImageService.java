package com.example.ImageUploadAndDownload.service;

import com.example.ImageUploadAndDownload.entity.Image;
import com.example.ImageUploadAndDownload.repository.ImageRepository;
//import com.example.ImageUploadAndDownload.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

//    private final String FOLDER_PATH="/Users/javatechie/Desktop/MyFIles/";

    public String uploadImage(MultipartFile file) throws IOException {
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .filetype(file.getContentType())
//                .filepath(ImageUtils.compressImage(file.getBytes())).build());
                .filepath(file.getBytes()).build());
        if (image != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }


    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
//        byte[] images = ImageUtils.decompressImage(dbImageData.get().getFilepath());
        byte[] images=dbImageData.get().getFilepath();
        return images;
    }
}
