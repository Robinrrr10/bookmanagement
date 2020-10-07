package com.github.bookmanagement;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.github.bookmanagement.service.BookService;

@ApplicationPath("v1")
public class BookApplication extends Application {

	private Set<Class<?>> allService = new HashSet<Class<?>>();
	
	public BookApplication() {
		System.out.println("Starting book application");
		allService.add(BookService.class);
		System.out.println("Started");
	}
	
	public Set<Class<?>> getClasses(){
		return allService;
	}
}
