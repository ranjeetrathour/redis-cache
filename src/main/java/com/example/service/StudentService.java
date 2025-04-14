package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    public Student getStudentById(String id) throws JsonProcessingException {
        final var resultFromCache = redisService.get(id);
        if (Objects.nonNull(resultFromCache)){
            return objectMapper.readValue(resultFromCache, Student.class);
        }else {
            final var studentFromDb =  studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found with this id"));
            if (Objects.nonNull(studentFromDb)){
                redisService.set(id,studentFromDb,1);
            }
            return studentFromDb;
        }
    }

    public Student saveStudent(Student student){
        try {
            System.out.println(student);
            final var v =  studentRepository.save(student);
            System.out.println(v);
            return v;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }
}
