package com.tao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tao.dao.BaseDAO;
import com.tao.model.Classes;
import com.tao.model.Course;
import com.tao.service.CourseService;

@Transactional
// 启用事务机制
@Service("CourseService")
public class CourseServiceImpl implements CourseService {
	@Resource
	private BaseDAO<Course> baseDAO;

	@Override
	public void saveCourse(Course course) {
		baseDAO.save(course);
	}

	@Override
	public void updateCourse(Course course) {
		baseDAO.update(course);
	}

	@Override
	public Course findCourseById(int id) {
		return baseDAO.get(Course.class, id);
	}

	@Override
	public void deleteCourse(Course course) {
		baseDAO.delete(course);
	}

	@Override
	public List<Course> findAllList() {
		return baseDAO.find("from Course");
	}

	@Override
	public List<Course> findByPage(int page, int rows) {
		return baseDAO.findByPage(Course.class, page, rows);
	}

	@Override
	public int getCount() {
		return baseDAO.getCount(Course.class);
	}
}
