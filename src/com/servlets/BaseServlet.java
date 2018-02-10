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
			//获取请求处理方法执行后返回的字符串，它表示转发或者重定向的路径！
			String result = (String) method.invoke(this, request, response);
			//完成转发或者重定向操作
			/*
			 * 查看返回的字符串中是否包含冒号，没有的话默认为转发
			 * 如果有，则使用冒号分割字符串，得到前缀和后缀
			 * 前缀如果是f，表示转发，如果是r，表示重定向
			 * 后缀就是要转发和重定向的路径
			 */
			if(result==null || result.trim().equals(""))
			{
				//返回的字符串为空
				return;
			}
			if(result.contains(":"))
			{
				int index = result.indexOf(":");//获取冒号位置
				String s = result.substring(0, index);//截取前缀
				String path = result.substring(index+1);//截取后缀
				if(s.equalsIgnoreCase("r")){//重定向操作
					response.sendRedirect(request.getContextPath()+path);
				}else if(s.equalsIgnoreCase("f")){//转发操作
					request.getRequestDispatcher(path).forward(request, response);
				}else{
					throw new RuntimeException("暂不支持此类操作！");
				}
			}else
			{
				//默认为转发操作
				request.getRequestDispatcher(result).forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println(methodName+"方法内部抛出异常！");
			throw new RuntimeException(e);
		}
	}

}
