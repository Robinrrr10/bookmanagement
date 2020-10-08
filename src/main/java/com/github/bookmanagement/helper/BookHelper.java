package com.github.bookmanagement.helper;

import java.util.ArrayList;
import java.util.List;

import com.github.bookmanagement.db.DBUtils;
import com.github.bookmanagement.entities.BookDetail;
import com.github.bookmanagement.entities.BookResponse;
import com.github.bookmanagement.entities.StatusDetail;

public class BookHelper {
	
	DBUtils dbUtils = new DBUtils();
	
	public BookResponse createNewBook(BookDetail bookDetail) {
		String bookName = bookDetail.getName();
		String bookDesc = bookDetail.getDescription();
		System.out.println("book name is: " + bookName);
		System.out.println("book description is: " + bookDesc);
		dbUtils.addBook(bookName, bookDesc);
		BookResponse bookResponse = new BookResponse();
		StatusDetail statusDetail = new StatusDetail();
		statusDetail.setStatusCode("1001");
		statusDetail.setMessage("Book created successfully");
		statusDetail.setType("success");
		bookResponse.setStatusDetail(statusDetail);
		return bookResponse;
	}
	
	public BookResponse getBooks() {
		List<BookDetail> bookDetails = new ArrayList<BookDetail>();
		List<String> booknames = dbUtils.getBooks();
		for(String name : booknames) {
			BookDetail bookDetail = new BookDetail();
			bookDetail.setName(name);
			bookDetails.add(bookDetail);
		}
		BookResponse bookResponse = new BookResponse();
		StatusDetail statusDetail = new StatusDetail();
		statusDetail.setStatusCode("1002");
		statusDetail.setMessage("Book details fetched successfully");
		statusDetail.setType("success");
		bookResponse.setStatusDetail(statusDetail);
		bookResponse.setData(bookDetails);
		return bookResponse;
	}

}
