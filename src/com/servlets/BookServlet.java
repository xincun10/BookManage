package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Book;
import com.services.BookService;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {

	private BookService service = new BookService();
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> list = service.findAll();
		//结果保存到request域当中
		request.setAttribute("bookList", list);
		//请求转发到/show.jsp
		return "/show.jsp";
	}
	
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("category");
		List<Book> list = service.findByCategory(Integer.parseInt(value));
		request.setAttribute("bookList", list);
		return "/show.jsp";
	}
}
