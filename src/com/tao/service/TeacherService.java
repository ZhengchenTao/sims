package com.tao.service;

import java.util.List;

import com.tao.model.Teacher;

public interface TeacherService {

	public void saveTeacher(Teacher teacher);

	public void updateTeacher(Teacher teacher);

	public Teacher findTeacherById(int id);

	public void deleteTeacher(Teacher teacher);

	public List<Teacher> findAllList();

	public List<Teacher> findByPage(int page, int rows);

	public int getCount();
}
