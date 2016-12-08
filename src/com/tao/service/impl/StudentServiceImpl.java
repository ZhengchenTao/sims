package com.tao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tao.dao.BaseDAO;
import com.tao.model.Student;
import com.tao.service.StudentService;

@Transactional
// 启用事务机制
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	private static final int Student = 0;
	@Resource
	private BaseDAO<Student> baseDAO;

	@Override
	public void saveStudent(Student student) {
		baseDAO.save(student);
	}

	@Override
	public void updateStudent(Student student) {
		baseDAO.update(student);
	}

	@Override
	public Student findStudentById(int id) {
		return baseDAO.get(Student.class, id);
	}

	@Override
	public void deleteStudent(Student student) {
		baseDAO.delete(student);
	}

	@Override
	public List<Student> findAllList() {
		return baseDAO.find("from Student");
	}

	@Override
	public List<Student> findByPage(int page, int rows) {
		return baseDAO.findByPage(Student.class, page, rows);
	}

	@Override
	public int getCount() {
		return baseDAO.getCount(Student.class);
	}

}
