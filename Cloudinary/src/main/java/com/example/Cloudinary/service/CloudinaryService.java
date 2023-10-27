package com.example.Cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Cloudinary.entity.Cloud;
import com.example.Cloudinary.repository.CloudinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    CloudinaryRepository cloudinaryRepository;

    @Autowired
    Cloudinary cloudinary;

    public Map upload(MultipartFile multipartFile) throws IOException {
        Map map = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
        return map;
    }

    public Map newUpload(MultipartFile multipartFile, Cloud cloud) throws IOException {
        Map map1 = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
        cloud.setId(cloud.getId());
        cloud.setName(cloud.getName());
        cloud.setEmail(cloud.getEmail());
        cloud.setPublicid((String) map1.get("public_id"));
        cloud.setUrl((String) map1.get("url"));
        cloudinaryRepository.save(cloud);
        return map1;
    }
}
