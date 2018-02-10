package com.services;

import java.util.List;

import com.beans.Book;
import com.daos.BookDao;

public class BookService {

	private BookDao dao = new BookDao();
	
	public List<Book> findAll()
	{
		return dao.findAll();
	}
	
	public List<Book> findByCategory(int category)
	{
		return dao.findByCategory(category);
	}
}
