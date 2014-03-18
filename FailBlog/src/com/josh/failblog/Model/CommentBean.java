package com.josh.failblog.Model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CommentBean implements Serializable {

	private static final long serialVersionUID = 1378177864360134644L;
	private Integer commentid;
	private Integer blogid;
	private String username;
	private String content;
	private Date date;
	
	public CommentBean() {
		content = "Leave a comment!";
	}
	
	public CommentBean(Integer commentid, Integer blogid, String username, String content) {
		this.commentid = commentid;
		this.blogid = blogid;
		this.username = username;
		this.content = content;
	}

	public Integer getCommentid() {
		return commentid;
	}

	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
