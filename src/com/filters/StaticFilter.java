package com.filters;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class StaticFilter
 */
@WebFilter("/BookServlet")
public class StaticFilter implements Filter {
	
	private FilterConfig config;
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * 1.第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
		 * 2.如果不存在，放行！把servlet访问数据库后，输出给客户端的数据保存到一个html中
		 */
		/*
		 * 一、获取category参数
		 * category有四种可能：
		 * 	null --> null.html
		 * 	1 --> 1.html
		 *  2 --> 2.html
		 *  3 --> 3.html
		 * html页面都保存到htmls目录下
		 */
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//得到相应的文件名称
		String htmlPath = config.getServletContext().getRealPath("/htmls");//得到文件的实际存储路径
		File destFile = new File(htmlPath, htmlPage);//到目的路径寻找要找的文件
		if(destFile.exists()){//文件存在
			//重定向到这个文件
			res.sendRedirect(req.getContextPath()+"/htmls/"+htmlPage);
			return;
		}
		/*
		 * 二、如果文件不存在，我们要生成html
		 * 	1.放行，show.jsp会做出很多的输出，我们要让它别再输出给客户端，而是输出到我们指定的一个html文件中
		 * 		如何实现？
		 * 		掉包response,让它的getWriter()与一个html文件绑定，那么show.jsp的输出就到了html文件中
		 */
		StaticResponse resp = new StaticResponse(res, destFile.getAbsolutePath());//自动创建不存在的文件
		//放行，即生成了html文件
		chain.doFilter(request, resp);
		//这时页面已经存在，重定向到html文件
		res.sendRedirect(req.getContextPath()+"/htmls/"+htmlPage);
	}

}
