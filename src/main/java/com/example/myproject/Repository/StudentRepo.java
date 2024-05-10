package com.example.myproject.Repository;

import com.example.myproject.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, String> {}
