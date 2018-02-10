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
		 * 1.��һ�η���ʱ�����������Ӧ��htmlҳ���Ƿ���ڣ���������ض���html
		 * 2.��������ڣ����У���servlet�������ݿ��������ͻ��˵����ݱ��浽һ��html��
		 */
		/*
		 * һ����ȡcategory����
		 * category�����ֿ��ܣ�
		 * 	null --> null.html
		 * 	1 --> 1.html
		 *  2 --> 2.html
		 *  3 --> 3.html
		 * htmlҳ�涼���浽htmlsĿ¼��
		 */
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//�õ���Ӧ���ļ�����
		String htmlPath = config.getServletContext().getRealPath("/htmls");//�õ��ļ���ʵ�ʴ洢·��
		File destFile = new File(htmlPath, htmlPage);//��Ŀ��·��Ѱ��Ҫ�ҵ��ļ�
		if(destFile.exists()){//�ļ�����
			//�ض�������ļ�
			res.sendRedirect(req.getContextPath()+"/htmls/"+htmlPage);
			return;
		}
		/*
		 * ��������ļ������ڣ�����Ҫ����html
		 * 	1.���У�show.jsp�������ܶ�����������Ҫ��������������ͻ��ˣ��������������ָ����һ��html�ļ���
		 * 		���ʵ�֣�
		 * 		����response,������getWriter()��һ��html�ļ��󶨣���ôshow.jsp������͵���html�ļ���
		 */
		StaticResponse resp = new StaticResponse(res, destFile.getAbsolutePath());//�Զ����������ڵ��ļ�
		//���У���������html�ļ�
		chain.doFilter(request, resp);
		//��ʱҳ���Ѿ����ڣ��ض���html�ļ�
		res.sendRedirect(req.getContextPath()+"/htmls/"+htmlPage);
	}

}
