package com.example.Cloudinary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cloudinary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cloud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String publicid;
    private String url;
}
