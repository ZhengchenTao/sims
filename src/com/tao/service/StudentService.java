package com.tao.service;

import java.util.List;

import com.tao.model.Student;

public interface StudentService {

	public void saveStudent(Student student);

	public void updateStudent(Student student);

	public Student findStudentById(int id);

	public void deleteStudent(Student student);

	public List<Student> findAllList();

	public List<Student> findByPage(int page, int rows);

	public int getCount();

}
