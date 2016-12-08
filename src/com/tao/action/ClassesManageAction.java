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
import com.tao.service.ClassesService;

@Controller
@ParentPackage("all")
@Namespace(value = "/classes")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class ClassesManageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private Classes classes;
	private int page = 1;
	private int rows = 9;

	@Resource
	private ClassesService classesService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/classes/classesIndex.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String index() {
		int count = classesService.getCount();
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
		List<Classes> classesList = classesService.findByPage(page, rows);
		if (classesList != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("list", classesList);
			ac.put("clist", classesList);
			ac.put("page", page);
			ac.put("cpage", cpage);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "delete", results = {
			@Result(type = "redirect", name = "success", location = "/classes/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String delete() {
		Classes s = classesService.findClassesById(id);
		classesService.deleteClasses(s);
		return SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/classes/classesEdit.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String edit() {
		Classes s = classesService.findClassesById(id);
		if (s != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("stu", s);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doEdit", results = {
			@Result(type = "redirect", name = "success", location = "/classes/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doEdit() {
		classesService.updateClasses(classes);
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/classes/classesAdd.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String add() {
			return SUCCESS;
	}

	@Action(value = "doAdd", results = {
			@Result(type = "redirect", name = "success", location = "/classes/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doAdd() {
		classesService.saveClasses(classes);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
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
