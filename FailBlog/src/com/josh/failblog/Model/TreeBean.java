package com.josh.failblog.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.josh.failblog.Utils.DBUtils;

@ManagedBean
@SessionScoped
public class TreeBean implements Serializable {
	private static final long serialVersionUID = 8264128011860953792L;
	private TreeNode root;
	private TreeNode selectedNode;

	public TreeBean() {
		root = new DefaultTreeNode("Root", null);

		ArrayList<TreeNode> tree = new ArrayList<TreeNode>();
		Map<String, Collection<BlogBean>> map = new HashMap<String, Collection<BlogBean>>();

		for (BlogBean blog : DBUtils.selectBlogs().values()) {
			Collection<BlogBean> blogs = map.get(blog.getDate().toString()
					.substring(0, 7));
			if (blogs == null) {
				blogs = new ArrayList<BlogBean>();
				map.put(blog.getDate().toString().substring(0, 7), blogs);
			}
			blogs.add(blog);
		}

		int i = 0;
		int j = 0;
		for (String date : map.keySet()) {
			tree.add(new DefaultTreeNode(date, root));
			for (BlogBean blog : map.get(date)) {
				tree.add(new DefaultTreeNode(blog, tree.get(i)));
				j += 1;
			}
			i += j + 1;
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public static void main(String[] args) {
		TreeBean t = new TreeBean();
		System.out.println("Root: " + t.getRoot());
		for (TreeNode node : t.getRoot().getChildren()) {
			System.out.println("Node: " + node);
			for (TreeNode leaf : node.getChildren()) {
				System.out.println("Leaf: " + ((BlogBean) leaf.getData()).getContent());
			}
		}
	}

}
