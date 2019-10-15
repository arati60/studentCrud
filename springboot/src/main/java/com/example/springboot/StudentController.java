package com.example.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Student;
import com.example.service.IStudentService;

@RestController
@RequestMapping("/user")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@GetMapping("student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	@RequestMapping("/arati")
	public String getSprin() {
		System.out.println("in arati call -------------------------------------------------------------");
		return "Hi arati from excelsoft";
	}
	
	@GetMapping("student/all")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> allUser=studentService.getAllStudent();
		return new ResponseEntity<List<Student>>(allUser,HttpStatus.OK);
	}
	
	@DeleteMapping("student/{id}")
	public ResponseEntity<Void>getDeleteStudent(@PathVariable("id") Integer id){
		studentService.deleteStudent(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@PutMapping("student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		studentService.updateStudent(student);
		return new ResponseEntity<Student>(student,HttpStatus.OK);
	}
	
	@PostMapping("student")
	public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder builder) {
                boolean flag = studentService.addStudent(student);
                if (flag == false) {
        	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/student/{id}").buildAndExpand(student.getStudentId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
