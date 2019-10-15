package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.IStudent;
import com.example.model.Student;

@Service
public class StudentService implements IStudentService {
	
	@Autowired
	private IStudent studentDAO;

	@Override
	public List<Student> getAllStudent() {
		return studentDAO.getAllStudent();
	}

	@Override
	public Student getStudentById(int studentId) {
		Student obj = studentDAO.getStudentById(studentId);
		return obj;
	}

	public synchronized boolean addStudent(Student student){
        if (studentDAO.studentExists(student.getStudentClass(), student.getCategory())) {
            return false;
        } else {
            studentDAO.addStudent(student);
            return true;
        }
}

	@Override
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	@Override
	public void deleteStudent(int studentId) {
		studentDAO.deleteStudent(studentId);
		
	}

}
