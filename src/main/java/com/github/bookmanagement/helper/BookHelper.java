package com.github.bookmanagement.helper;

import com.github.bookmanagement.entities.BookDetail;
import com.github.bookmanagement.entities.BookResponse;
import com.github.bookmanagement.entities.StatusDetail;

public class BookHelper {
	
	public BookResponse createNewBook(BookDetail bookDetail) {
		String bookName = bookDetail.getName();
		String bookDesc = bookDetail.getDescription();
		System.out.println("book name is: " + bookName);
		System.out.println("book description is: " + bookDesc);
		
		// call db method which will create new book in dh
		
		BookResponse bookResponse = new BookResponse();
		StatusDetail statusDetail = new StatusDetail();
		statusDetail.setStatusCode("1001");
		statusDetail.setMessage("Book created successfully");
		statusDetail.setType("success");
		bookResponse.setStatusDetail(statusDetail);
		return bookResponse;
	}
	
	public BookResponse getBooks() {

		
		// db method is called to get all available books
		
		BookResponse bookResponse = new BookResponse();
		StatusDetail statusDetail = new StatusDetail();
		statusDetail.setStatusCode("1002");
		statusDetail.setMessage("Book details fetched successfully");
		statusDetail.setType("success");
		bookResponse.setStatusDetail(statusDetail);
		return bookResponse;
	}

}
