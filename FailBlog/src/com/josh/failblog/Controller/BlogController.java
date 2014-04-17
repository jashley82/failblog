package com.josh.failblog.Controller;

import java.io.Serializable;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.josh.failblog.Model.BlogBean;
import com.josh.failblog.Utils.DBUtils;

@ManagedBean
@SessionScoped
public class BlogController implements Serializable {
	private static final long serialVersionUID = 4281160765162472478L;
	private BlogBean blog;
	private Integer blogid;
	private Integer userid;
	private boolean editable;

	public BlogController() {
		setBlog(DBUtils.selectBlog(1));
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public BlogBean getBlog() {
		return blog;
	}

	public void setBlog(BlogBean blog) {
		this.blog = blog;
	}

	public BlogBean[] getBlogs() {
		TreeMap<Integer, BlogBean> tmp = DBUtils.selectBlogs();
		return tmp.values().toArray(new BlogBean[tmp.size()]);
	}

	public void setblogid(Integer blogid) {
		this.blogid = blogid;
	}

	public Integer getblogid() {
		return blogid;
	}

	public Integer getuserid() {
		return userid;
	}

	public void setuserid(Integer userid) {
		this.userid = userid;
	}

	public String renderBlog() {
		setblogid(blog.getBlogid());
		setEditable(false);
		return null;
	}

	public String newBlog() {
		setBlog(new BlogBean("", "", "", -1, userid, null));
		setEditable(true);
		return null;
	}

	public String saveBlog() {
		DBUtils.insertBlog(blog);
		setEditable(false);
		return null;
	}

	public String editBlog() {
		setEditable(true);
		return null;
	}

	public String removeBlog() {
		DBUtils.deleteBlog(blogid);
		setBlog(new BlogBean());
		setEditable(true);
		return null;
	}

	public String cancelBlog() {
		setEditable(false);
		setBlog(DBUtils.selectBlog(blogid));
		return null;
	}

	public static void main(String[] args) {

	}

}
