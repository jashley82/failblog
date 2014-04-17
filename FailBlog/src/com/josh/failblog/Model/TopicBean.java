package com.josh.failblog.Model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TopicBean {
	private String name;
	private List<BlogBean> blogs;

	public TopicBean() {}
	
	public TopicBean(String name, List<BlogBean> blogs) {
		this.name = name;
		this.blogs = blogs;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<BlogBean> getBlogs() {
		return blogs;
	}

	public String toString() {
		return name;
	}
}
