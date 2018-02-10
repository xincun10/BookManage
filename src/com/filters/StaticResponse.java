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
	 * 调包response
	 * @param response
	 * @param path printWriter对应的文件输出路径。
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public StaticResponse(HttpServletResponse response, String path) throws FileNotFoundException, UnsupportedEncodingException {
		super(response);
		this.response = response;
		//创建一个与html文件绑定在一起的流对象
		pw = new PrintWriter(path, "utf-8");
	}

	public PrintWriter getWriter()
	{
		//返回一个与html绑定在一起的PrintWriter对象
		//jsp会使用它进行输出，这样数据都输出到html文件中了
		return pw;
	}
}
