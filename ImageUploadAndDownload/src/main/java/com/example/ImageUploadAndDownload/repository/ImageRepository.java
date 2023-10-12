package com.example.ImageUploadAndDownload.repository;

import com.example.ImageUploadAndDownload.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer> {

    Optional<Image> findByName(String fileName);
}
