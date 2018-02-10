package com.filters;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {

	private HttpServletResponse response;
	private PrintWriter pw;
	
	/**
	 * ����response
	 * @param response
	 * @param path printWriter��Ӧ���ļ����·����
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public StaticResponse(HttpServletResponse response, String path) throws FileNotFoundException, UnsupportedEncodingException {
		super(response);
		this.response = response;
		//����һ����html�ļ�����һ���������
		pw = new PrintWriter(path, "utf-8");
	}

	public PrintWriter getWriter()
	{
		//����һ����html����һ���PrintWriter����
		//jsp��ʹ��������������������ݶ������html�ļ�����
		return pw;
	}
}
