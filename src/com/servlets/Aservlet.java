package com.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Aservlet
 */
@WebServlet("/Aservlet")
public class Aservlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ÃÌº”Õº È");
	}

	public void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("±‡º≠Õº È");
	}
	
	public void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("…æ≥˝Õº È");
	}

}
