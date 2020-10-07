package com.github.bookmanagement.entities;

import java.util.List;

public class BookResponse {

	private StatusDetail statusDetail;
	private List<BookDetail> data;
	public StatusDetail getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(StatusDetail statusDetail) {
		this.statusDetail = statusDetail;
	}
	public List<BookDetail> getData() {
		return data;
	}
	public void setData(List<BookDetail> data) {
		this.data = data;
	}
	
}
