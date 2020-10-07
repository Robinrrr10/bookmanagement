package com.github.bookmanagement.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.bookmanagement.entities.BookDetail;
import com.github.bookmanagement.entities.BookResponse;
import com.github.bookmanagement.helper.BookHelper;

@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/book")
public class BookService {

	BookHelper bookHelper = new BookHelper();
	
	@POST
	@Path("/create")
	public BookResponse createBook(BookDetail bookDetail) {
		return bookHelper.createNewBook(bookDetail);
	}
	
	@GET
	@Path("/allbooks")
	public BookResponse getAllBooks() {
		return bookHelper.getBooks();
	}
}
