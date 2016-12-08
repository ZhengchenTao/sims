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
import com.tao.model.Teacher;
import com.tao.service.ClassesService;
import com.tao.service.TeacherService;

@Controller
@ParentPackage("all")
@Namespace("/teacher")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class TeacherManageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private Teacher teacher;
	private int page = 1;
	private int rows = 9;
	@Resource
	private TeacherService teacherService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/teacher/teacherIndex.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String index() {
		int count = teacherService.getCount();
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
		List<Teacher> teacherList = teacherService.findByPage(page, rows);
		if (teacherList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("list", teacherList);
			ac.put("page", page);
			ac.put("cpage", cpage);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "delete", results = {
			@Result(type = "redirect", name = "success", location = "/teacher/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String delete() {
		Teacher s = teacherService.findTeacherById(id);
		teacherService.deleteTeacher(s);
		return SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/teacher/teacherEdit.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String edit() {
		Teacher s = teacherService.findTeacherById(id);
		if (s != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("tea", s);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doEdit", results = {
			@Result(type = "redirect", name = "success", location = "/teacher/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doEdit() {
		teacherService.updateTeacher(teacher);
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/teacher/teacherAdd.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String add() {
		return SUCCESS;
	}

	@Action(value = "doAdd", results = {
			@Result(type = "redirect", name = "success", location = "/teacher/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doAdd() {
		teacherService.saveTeacher(teacher);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
