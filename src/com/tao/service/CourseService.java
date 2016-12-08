package com.tao.service;

import java.util.List;

import com.tao.model.Course;

public interface CourseService {

	public void saveCourse(Course course);

	public void updateCourse(Course course);

	public Course findCourseById(int id);

	public void deleteCourse(Course course);

	public List<Course> findAllList();

	public List<Course> findByPage(int page, int rows);

	public int getCount();
}
