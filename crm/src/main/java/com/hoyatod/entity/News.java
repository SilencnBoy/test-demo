package com.hoyatod.entity;

import java.io.Serializable;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String context;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", context=" + context + "]";
	}

}
