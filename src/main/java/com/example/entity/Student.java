package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    private String uuid;
    private String name;
    private String age;
    private String city;

    @PrePersist
    void onCreating(){
        uuid= UUID.randomUUID().toString();
    }
}
