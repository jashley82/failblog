package com.josh.failblog.Controller;

import java.io.Serializable;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.josh.failblog.Model.CommentBean;
import com.josh.failblog.Utils.DBUtils;

@ManagedBean
@SessionScoped
public class CommentController implements Serializable {

	private static final long serialVersionUID = 4965874875046092934L;
	private CommentBean comment;
	private Integer blogid;
	private String username;

	public CommentController() {
		blogid = 1;
		comment = new CommentBean();
	}
	
	public CommentBean getComment() {
		return comment;
	}

	public void setComment(CommentBean comment) {
		this.comment = comment;
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

	public CommentBean[] getComments() {
		TreeMap<Integer, CommentBean> tmp = DBUtils.selectComments(blogid);
		return tmp.values().toArray(new CommentBean[tmp.size()]);
	}

	public String saveComment() {
		if (username.equals(""))
			username = "Anonymous";
		comment.setUsername(username);
		comment.setBlogid(blogid);
		DBUtils.insertComment(comment);
		comment = new CommentBean();
		return null;
	}
	
	public String cancelComment() {
		comment = new CommentBean();
		return null;
	}
	
	public static void main(String[] args) {
		TreeMap<Integer, CommentBean> tmp = DBUtils.selectComments(1);
		for (CommentBean commentBean : tmp.values().toArray(new CommentBean[tmp.size()])) {
			System.out.println(commentBean.getContent());
		}
	}
}
