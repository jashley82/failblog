package com.josh.failblog.Controller;

import java.io.Serializable;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.TreeNode;

import com.josh.failblog.Model.BlogBean;
import com.josh.failblog.Model.TreeBean;
import com.josh.failblog.Utils.DBUtils;

@ManagedBean
@SessionScoped
public class BlogController implements Serializable {
	private static final long serialVersionUID = 4281160765162472478L;
	private BlogBean blog;
	private Integer blogid;
	private Integer userid;
	private boolean editable;
	private TreeNode node;

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

	public TreeNode getNode() {
		return node;
	}

	public void setNode(TreeNode node) {
		this.node = node;
	}

	public String renderBlog() {
		setBlog(DBUtils.selectBlog(blogid));
		// setBlog((BlogBean) node.getData());
		System.out.println("Heheheheheheheh");
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
		System.out.println("Yoyoyoyoyoy");
		setEditable(false);
		setBlog(DBUtils.selectBlog(blogid));
		return null;
	}

	public static void main(String[] args) {
		TreeBean t = new TreeBean();
		BlogController bc = new BlogController();
		for (TreeNode node : t.getRoot().getChildren()) {
			for (TreeNode leaf : node.getChildren()) {
				bc.setBlog((BlogBean) leaf.getData());
				System.out.println(bc.getBlog().getTitle());
			}
		}

	}

}
