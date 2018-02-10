package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BServlet")
public class BServlet extends BaseServlet {

	public String fun1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("fun1().....");
		//完成转发到Webroot/index.jsp
		return "f:/index.jsp";
	}
	
	public String fun2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("fun2().....");
		//完成重定向到Webroot/index.jsp
		return "r:/index.jsp";
	}
	
	public String fun3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("fun3().....");
		//什么也不做
		return null;
	}
}
