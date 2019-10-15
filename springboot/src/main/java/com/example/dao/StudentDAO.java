package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.example.model.Student;

@Repository
@Transactional

public class StudentDAO implements IStudent {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudent() {
		
		String hql = "FROM Student";
		Query query =  entityManager.createQuery(hql);
		
		return (List<Student>) query.getResultList();
	}

	@Override
	public Student getStudentById(int id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public void addStudent(Student addStudent) {
		entityManager.persist(addStudent);
		
	}

	@Override
	public void updateStudent(Student upStudent) {
		Student artcl = getStudentById(upStudent.getStudentId());
		artcl.setStudentClass(upStudent.getStudentClass());
		artcl.setCategory(upStudent.getCategory());
		entityManager.flush();
		
	}

	@Override
	public void deleteStudent(int articleId) {
		entityManager.remove(getStudentById(articleId));
		
	}

	@Override
	public boolean studentExists(String studentClass, String category) {
		String hql = "FROM Article as atcl WHERE atcl.title=?1 and atcl.category=?2";
		int count = entityManager.createQuery(hql).setParameter(1, studentClass)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}

}
