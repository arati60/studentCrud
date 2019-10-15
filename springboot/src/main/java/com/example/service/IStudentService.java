package com.example.service;

import java.util.List;

import com.example.model.Student;

public interface IStudentService {
	List<Student> getAllStudent();
    Student getStudentById(int studentId);
    boolean addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int studentId);

}
