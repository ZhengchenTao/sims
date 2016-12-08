package com.tao.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.tao.action.IndexAction;

public class LoginInterceptor implements Interceptor {

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
		//System.out.println("====invocation====");
		//Object action = invocation.getAction();
		//if (action instanceof IndexAction) {
		//	return invocation.invoke();
		//}
		ActionContext ac = invocation.getInvocationContext();
		Object uname = ac.getSession().get("username");
		if (uname == null || uname == "") {
			return "login";
		}
		return invocation.invoke();
	}

}
