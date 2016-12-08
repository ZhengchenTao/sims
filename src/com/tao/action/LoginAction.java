package com.tao.action;

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

@Controller
@ParentPackage("struts-default")
@Namespace(value = "/")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class LoginAction extends ActionSupport {
	private Admin admin;
	@Resource
	private AdminService adminService;

	@Action(value = "login", results = {
			@Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/login.jsp") })
	public String login() {
		ActionContext ac = ActionContext.getContext();
		if (ac.getSession().get("username") != null
				&& ac.getSession().get("userid") != "") {
			ac.getSession().remove("me");
			return SUCCESS;
		}
		if (admin == null) {
			ac.getSession().put("me", "用户名密码错误");
			return ERROR;
		}
		Admin ad = adminService.Login(admin);
		if (ad != null) {
			ac.getSession().put("username", ad.getUsername());
			ac.getSession().put("userid", ad.getId());
			ac.getSession().remove("me");
			return SUCCESS;
		}
		ac.getSession().put("me", "用户名密码错误");
		return ERROR;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
