package com.josh.failblog.Utils;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.rowset.serial.SerialBlob;

import com.josh.failblog.Model.BlogBean;
import com.josh.failblog.Model.CommentBean;
import com.josh.failblog.Model.UserBean;

@ManagedBean
@RequestScoped
public class DBUtils implements Serializable {

	private static final long serialVersionUID = 3463744743959512812L;

	public static void insertBlog(BlogBean blogBean) {
		String command;
		if (blogBean.getBlogid() == -1) {
			command = "INSERT INTO Blogs (title, content, meta, userid) "
					+ "VALUES (?, ?, ?, ?);";
			try {
				PreparedStatement stmt = DBConnector.connect()
						.prepareStatement(command);
				stmt.setString(1, blogBean.getTitle());
				stmt.setString(2, blogBean.getContent());
				stmt.setString(3, blogBean.getMeta());
				stmt.setInt(4, blogBean.getUserid());
				stmt.executeUpdate();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			command = "UPDATE Blogs SET "
					+ "title = ?, content = ?, meta = ? WHERE blogid = ?;";
			try {
				PreparedStatement stmt = DBConnector.connect()
						.prepareStatement(command);
				stmt.setString(1, blogBean.getTitle());
				stmt.setString(2, blogBean.getContent());
				stmt.setString(3, blogBean.getMeta());
				stmt.setInt(4, blogBean.getBlogid());
				stmt.executeUpdate();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteBlog(Integer blogid) {
		String command = "UPDATE Blogs SET deleted = TRUE WHERE blogid = ?;";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			stmt.setInt(1, blogid);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BlogBean selectBlog(Integer blogid) {
		String command = "SELECT * FROM Blogs WHERE blogid = ? AND deleted = FALSE;";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			stmt.setInt(1, blogid);
			ResultSet rs = stmt.executeQuery();
			BlogBean blog = new BlogBean();
			while (rs.next()) {
				blog.setBlogid(rs.getInt("blogid"));
				blog.setTitle(rs.getString("title"));
				blog.setContent(rs.getString("content"));
				blog.setMeta(rs.getString("meta"));
				blog.setUserid(rs.getInt("userid"));
				blog.setDate(rs.getDate("date"));
			}
			rs.close();
			return blog;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static TreeMap<Integer, BlogBean> selectBlogs(Integer userid) {
		String command = "SELECT * FROM Blogs WHERE userid = ? AND deleted = FALSE;";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			TreeMap<Integer, BlogBean> blogMap = new TreeMap<Integer, BlogBean>();
			while (rs.next()) {
				blogMap.put(
						rs.getInt("blogid"),
						new BlogBean(rs.getString("title"), rs
								.getString("content"), rs.getString("meta"), rs
								.getInt("blogid"), rs.getInt("userid"), rs
								.getDate("date")));
			}
			rs.close();
			return blogMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static TreeMap<Integer, BlogBean> selectBlogs() {
		String command = "SELECT * FROM Blogs WHERE deleted = FALSE";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			ResultSet rs = stmt.executeQuery();
			TreeMap<Integer, BlogBean> blogMap = new TreeMap<Integer, BlogBean>();
			while (rs.next()) {
				blogMap.put(
						rs.getInt("blogid"),
						new BlogBean(rs.getString("title"), rs
								.getString("content"), rs.getString("meta"), rs
								.getInt("blogid"), rs.getInt("userid"), rs
								.getDate("date")));
			}
			rs.close();
			return blogMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static TreeMap<Integer, CommentBean> selectComments(Integer blogid) {
		String command = "SELECT * FROM Comments WHERE blogid = ?;";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			stmt.setInt(1, blogid);
			ResultSet rs = stmt.executeQuery();
			TreeMap<Integer, CommentBean> commentMap = new TreeMap<Integer, CommentBean>();
			while (rs.next()) {
				commentMap.put(
						rs.getInt("commentid"),
						new CommentBean(rs.getInt("commentid"), rs
								.getInt("blogid"), rs.getString("username"), rs
								.getString("content")));
			}
			rs.close();
			return commentMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insertComment(CommentBean commentBean) {
		String command;
		command = "INSERT INTO Comments (blogid, username, content) "
				+ "VALUES (?, ?, ?);";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			stmt.setInt(1, commentBean.getBlogid());
			stmt.setString(2, commentBean.getUsername());
			stmt.setString(3, commentBean.getContent());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserBean lookupUser(String username) {
		String command = "SELECT * FROM Users WHERE username = ?;";
		try {
			PreparedStatement stmt = DBConnector.connect().prepareStatement(
					command);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			UserBean user = null;
			if (rs.first()) {
				user = new UserBean();
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setUserid(rs.getInt("userid"));
				Blob pw = rs.getBlob("password");
				user.setPasswordEncrypted(pw.getBytes(1, (int) pw.length()));
				Blob salt = rs.getBlob("salt");
				user.setSalt(salt.getBytes(1, (int) salt.length()));
			}
			rs.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insertUser(UserBean user) {
		String command = "INSERT INTO Users (username, password, firstname, lastname, email, salt) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt;
		try {
			stmt = DBConnector.connect().prepareStatement(command);
			stmt.setString(1, user.getUsername());

			Blob blob = new SerialBlob(user.getPasswordEncrypted());
			blob.setBytes(1, user.getPasswordEncrypted());

			stmt.setBlob(2, blob);
			stmt.setString(3, user.getFirstname());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getEmail());
			stmt.setBytes(6, user.getSalt());

			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePassword() {

	}

	public static void deleteUser() {

	}

	public static void main(String[] args) {
		TreeMap<Integer, BlogBean> tmp = DBUtils.selectBlogs(1);
		for (BlogBean blog : tmp.values().toArray(new BlogBean[tmp.size()])) {
			System.out.println(blog.getDate());
		}
	}

}
