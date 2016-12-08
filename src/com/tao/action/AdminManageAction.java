package com.tao.action;

import java.util.List;
import java.util.Map;

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
import com.tao.model.Admin;
import com.tao.service.AdminService;
import com.tao.service.ClassesService;

@Controller
@ParentPackage("admin")
@Namespace(value = "/admin")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class AdminManageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private Admin admin;
	private int page = 1;
	private int rows = 9;

	@Resource
	private AdminService adminService;
	@Resource
	private ClassesService classesService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/admin/adminIndex.jsp"),
			@Result(name = "error", location = "/error.jsp")})
	public String index() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().remove("me");
		int count = adminService.getCount();
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
		List<Admin> adminList = adminService.findByPage(page, rows);
		if (adminList != null) {
			ac.put("list", adminList);
			ac.put("page", page);
			ac.put("cpage", cpage);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "delete", results = {
			@Result(type = "redirect", name = "success", location = "/admin/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String delete() {
		Admin s = adminService.findAdminById(id);
		adminService.deleteAdmin(s);
		return SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/adminEdit.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String edit() {
		Admin s = adminService.findAdminById(id);
		if (s != null) {
			ActionContext ac = ActionContext.getContext();
			ac.put("stu", s);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	@Action(value = "doEdit", results = {
			@Result(type = "redirect", name = "success", location = "/admin/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doEdit() {
		adminService.updateAdmin(admin);
		return SUCCESS;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/admin/adminAdd.jsp"),
			@Result(name = "input", location = "/error.jsp") })
	public String add() {
		return SUCCESS;
	}

	@Action(value = "doAdd", results = {
			@Result(type = "redirect", name = "success", location = "/admin/index.action"),
			@Result(name = "input", location = "/error.jsp") })
	public String doAdd() {
		adminService.saveAdmin(admin);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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
