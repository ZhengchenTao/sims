package com.tao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tao.dao.BaseDAO;
import com.tao.model.Student;
import com.tao.model.Teacher;
import com.tao.service.TeacherService;

@Transactional
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private BaseDAO<Teacher> baseDAO;

	@Override
	public void saveTeacher(Teacher teacher) {
		baseDAO.save(teacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		baseDAO.update(teacher);
	}

	@Override
	public Teacher findTeacherById(int id) {
		return baseDAO.get(Teacher.class, id);
	}

	@Override
	public void deleteTeacher(Teacher teacher) {
		baseDAO.delete(teacher);
	}

	@Override
	public List<Teacher> findAllList() {
		return baseDAO.find("from Teacher");
	}

	@Override
	public List<Teacher> findByPage(int page, int rows) {
		return baseDAO.findByPage(Teacher.class, page, rows);
	}

	@Override
	public int getCount() {
		return baseDAO.getCount(Teacher.class);
	}

}
