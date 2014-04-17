package com.josh.failblog.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.josh.failblog.Model.BlogBean;
import com.josh.failblog.Model.TopicBean;

@ManagedBean
@SessionScoped
public class TopicController {
	private TopicBean topic;
	private List<TopicBean> topics;

	public TopicController() {
		List<BlogBean> lst = new ArrayList<BlogBean>();
		lst.add(new BlogBean("Test1", "Test1", null, null, null, new Date()));
		lst.add(new BlogBean("Test2", "Test2", null, null, null, new Date()));
		lst.add(new BlogBean("Test3", "Test3", null, null, null, new Date()));
		
		topics = new ArrayList<TopicBean>();
		topics.add(new TopicBean("Date", lst));
	}

	public TopicBean getTopic() {
		return topic;
	}

	public void setTopic(TopicBean topic) {
		this.topic = topic;
	}

	public List<TopicBean> getTopics() {
		return topics;
	}

}
