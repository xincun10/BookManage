package com.servlets;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取参数，用来识别用户想请求的方法
		 * 2.然后判断是哪一个方法，是哪一个就调用哪一个
		 */
		String methodName = request.getParameter("method");
		if(methodName==null || methodName.trim().isEmpty())
		{
			throw new RuntimeException("需要传递method参数！");
		}
		/*
		 * 通过反射获取想要调用的方法的名称
		 */
		Class clazz = this.getClass();//得到当前类的class对象
		Method method = null;
		try {
			method = clazz.getMethod(methodName, 
						HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			System.out.println("方法"+methodName+"不存在！");
			throw new RuntimeException(e);
		}
		/*
		 * 调用method表示的方法
		 */
		try {
			method.invoke(this, request, response);
		} catch (Exception e) {
			System.out.println(methodName+"方法内部抛出异常！");
			throw new RuntimeException(e);
		}
	}

}
