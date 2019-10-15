package com.example.dao;

import java.util.List;

import com.example.model.Student;

public interface  IStudent{
	List<Student>getAllStudent();
	Student  getStudentById(int id);
	void addStudent(Student addStudent);
	void updateStudent(Student upStudent);
	void deleteStudent(int studentId);
	boolean studentExists(String student_class, String category);

}
