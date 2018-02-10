package com.servlets;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
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
			//��ȡ��������ִ�к󷵻ص��ַ���������ʾת�������ض����·����
			String result = (String) method.invoke(this, request, response);
			//���ת�������ض������
			/*
			 * �鿴���ص��ַ������Ƿ����ð�ţ�û�еĻ�Ĭ��Ϊת��
			 * ����У���ʹ��ð�ŷָ��ַ������õ�ǰ׺�ͺ�׺
			 * ǰ׺�����f����ʾת���������r����ʾ�ض���
			 * ��׺����Ҫת�����ض����·��
			 */
			if(result==null || result.trim().equals(""))
			{
				//���ص��ַ���Ϊ��
				return;
			}
			if(result.contains(":"))
			{
				int index = result.indexOf(":");//��ȡð��λ��
				String s = result.substring(0, index);//��ȡǰ׺
				String path = result.substring(index+1);//��ȡ��׺
				if(s.equalsIgnoreCase("r")){//�ض������
					response.sendRedirect(request.getContextPath()+path);
				}else if(s.equalsIgnoreCase("f")){//ת������
					request.getRequestDispatcher(path).forward(request, response);
				}else{
					throw new RuntimeException("�ݲ�֧�ִ��������");
				}
			}else
			{
				//Ĭ��Ϊת������
				request.getRequestDispatcher(result).forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println(methodName+"�����ڲ��׳��쳣��");
			throw new RuntimeException(e);
		}
	}

}
