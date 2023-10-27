package com.example.Cloudinary.repository;

import com.example.Cloudinary.entity.Cloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryRepository extends JpaRepository<Cloud, Integer> {

}
