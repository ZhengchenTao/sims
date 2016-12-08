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
import com.tao.model.Student;
import com.tao.service.ClassesService;
import com.tao.service.StudentService;

@Controller
@ParentPackage("all")
@Namespace(value = "/student")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class StudentManageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private Student student;
	private int page = 1;
	private int rows = 9;

	@Resource
	private StudentService studentService;
	@Resource
	private ClassesService classesService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/student/studentIndex.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String index() {
		int count = studentService.getCount();
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
		List<Student> studentList = studentService.findByPage(page, rows);
		List<Classes> classesList = classesService.findAllList();
		if (studentList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("list", studentList);
			ac.put("clist", classesList);
			ac.put("page", page);
			ac.put("cpage", cpage);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "delete", results = {
			@Result(type = "redirect", name = "success", location = "/student/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String delete() {
		Student s = studentService.findStudentById(id);
		studentService.deleteStudent(s);
		return SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/student/studentEdit.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String edit() {
		Student s = studentService.findStudentById(id);
		List<Classes> classesList = classesService.findAllList();
		if (classesList != null && s != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("stu", s);
			ac.put("list", classesList);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doEdit", results = {
			@Result(type = "redirect", name = "success", location = "/student/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doEdit() {
		studentService.updateStudent(student);
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/student/studentAdd.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String add() {
		List<Classes> classesList = classesService.findAllList();
		if (classesList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("list", classesList);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doAdd", results = {
			@Result(type = "redirect", name = "success", location = "/student/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doAdd() {
		studentService.saveStudent(student);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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
