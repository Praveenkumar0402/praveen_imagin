package com.example.Cloudinary.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary config(){
        Map abc = new HashMap();
        abc.put("cloud_name","ditzny9gd");
        abc.put("api_key","946538379373348");
        abc.put("api_secret","-dUIxTue1nRDWGGNAVvIgZOQSYg");
        abc.put("secure",true);
        return new Cloudinary(abc);
    }
}
