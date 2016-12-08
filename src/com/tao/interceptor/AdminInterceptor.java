package com.tao.interceptor;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.tao.action.IndexAction;
import com.tao.model.Admin;
import com.tao.service.AdminService;

public class AdminInterceptor implements Interceptor {
	@Resource
	private AdminService adminService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("====invocation====");
		// Object action = invocation.getAction();
		// if (action instanceof IndexAction) {
		// return invocation.invoke();
		// }
		ActionContext ac = invocation.getInvocationContext();
		if (ac.getSession().get("userid") == null) {
			return "login";
		}
		int id = (int) ac.getSession().get("userid");
		Admin ad = adminService.findAdminById(id);
		if (ad.getRole().equals("1")) {
			return invocation.invoke();
		}
		ac.getSession().put("me", "您没有权限");
		return "role";
	}

}
