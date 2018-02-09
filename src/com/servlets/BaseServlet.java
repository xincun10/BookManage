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
		 * 1.��ȡ����������ʶ���û�������ķ���
		 * 2.Ȼ���ж�����һ������������һ���͵�����һ��
		 */
		String methodName = request.getParameter("method");
		if(methodName==null || methodName.trim().isEmpty())
		{
			throw new RuntimeException("��Ҫ����method������");
		}
		/*
		 * ͨ�������ȡ��Ҫ���õķ���������
		 */
		Class clazz = this.getClass();//�õ���ǰ���class����
		Method method = null;
		try {
			method = clazz.getMethod(methodName, 
						HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			System.out.println("����"+methodName+"�����ڣ�");
			throw new RuntimeException(e);
		}
		/*
		 * ����method��ʾ�ķ���
		 */
		try {
			method.invoke(this, request, response);
		} catch (Exception e) {
			System.out.println(methodName+"�����ڲ��׳��쳣��");
			throw new RuntimeException(e);
		}
	}

}
