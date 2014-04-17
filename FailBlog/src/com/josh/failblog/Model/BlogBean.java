package com.josh.failblog.Model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BlogBean implements Serializable {

	private static final long serialVersionUID = 3125763964136922201L;
	private Integer blogid = -1;
	private String title = "";
	private String content = "";
	private String meta = "";
	private Date date;
	private Integer userid;

	public BlogBean() {
	}

	public BlogBean(String title, String content, String meta, Integer blogid,
			Integer userid, Date date) {
		this.title = title;
		this.content = content;
		this.meta = meta;
		this.blogid = blogid;
		this.userid = userid;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer idNumber) {
		this.blogid = idNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String toString() {
		return title;
	}

}
