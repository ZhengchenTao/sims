package com.tao.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tao.model.Admin;
import com.tao.service.AdminService;

@Controller
@ParentPackage("all")
@Namespace(value = "/")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Admin admin;
	@Resource
	private AdminService adminService;

	@Action(value = "index", results = {
			@Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/login.jsp") })
	public String index() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().remove("me");
		return SUCCESS;
	}

	@Action(value = "logout", results = {
			@Result(name = "success", location = "/login.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String logout() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().remove("username");
		ac.getSession().remove("userid");
		return SUCCESS;
	}

	@Action(value = "alterPassword", results = {
			@Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String alterPassword() {
		ActionContext ac = ActionContext.getContext();
		Admin ad = new Admin();
		ad.setId((int) ac.getSession().get("userid"));
		ad.setUsername(ac.getSession().get("username").toString());
		Admin adm = adminService.Login(ad);
		adm.setPassword(admin.getPassword());
		adminService.updateAdmin(adm);
		return SUCCESS;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
