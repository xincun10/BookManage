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
		//���ת����Webroot/index.jsp
		return "f:/index.jsp";
	}
	
	public String fun2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("fun2().....");
		//����ض���Webroot/index.jsp
		return "r:/index.jsp";
	}
	
	public String fun3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("fun3().....");
		//ʲôҲ����
		return null;
	}
}
