package com.tao.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tao.model.Classes;
import com.tao.model.Course;
import com.tao.service.ClassesService;
import com.tao.service.CourseService;

@Controller
@ParentPackage("all")
@Namespace(value = "/course")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class CourseManageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private Course course;
	private int page = 1;
	private int rows = 9;

	@Resource
	private CourseService courseService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/course/courseIndex.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String index() {
		int count = courseService.getCount();
		int cpage;
		if (count % rows == 0) {
			cpage = (count / rows);
		} else {
			cpage = (count / rows) + 1;
		}
		if (page < 1) {
			this.page = 1;
		} else if (page > cpage) {
			page = cpage;
		}
		List<Course> courseList = courseService.findByPage(page, rows);
		if (courseList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("list", courseList);
			ac.put("page", page);
			ac.put("cpage", cpage);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "delete", results = {
			@Result(type = "redirect", name = "success", location = "/course/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String delete() {
		Course s = courseService.findCourseById(id);
		courseService.deleteCourse(s);
		return SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/course/courseEdit.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String edit() {
		Course s = courseService.findCourseById(id);
		if (s != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("stu", s);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doEdit", results = {
			@Result(type = "redirect", name = "success", location = "/course/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doEdit() {
		courseService.updateCourse(course);
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/course/courseAdd.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String add() {
		return SUCCESS;
	}

	@Action(value = "doAdd", results = {
			@Result(type = "redirect", name = "success", location = "/course/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doAdd() {
		courseService.saveCourse(course);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
