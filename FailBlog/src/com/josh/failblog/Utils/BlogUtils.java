package com.josh.failblog.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.josh.failblog.Model.BlogBean;

@ManagedBean
@SessionScoped
public class BlogUtils {
	private Map<String, Collection<BlogBean>> map;
	Collection<BlogBean> vals;
	private boolean key;
	private String sort;

	public BlogUtils() {
		map = new HashMap<String, Collection<BlogBean>>();
		String index;
		for (BlogBean blog : DBUtils.selectBlogs().values()) {
			index = blog.getDate().toString().substring(0, 7);
			Collection<BlogBean> blogs = map.get(index);
			if (blogs == null) {
				blogs = new ArrayList<BlogBean>();
				map.put(index, blogs);
			}
			blogs.add(blog);
		}
	}

	public boolean isKey() {
		return key;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String[] getKeySet() {
		Set<String> keys = map.keySet();
		return keys.toArray(new String[keys.size()]);
	}

	public BlogBean[] getVals() {
		return vals.toArray(new BlogBean[vals.size()]);
	}
	
	public String renderVals(String key) {
		vals = map.get(key);
		this.key = false;
		return null;
	}

}
