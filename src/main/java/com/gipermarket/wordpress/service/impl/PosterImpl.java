package com.gipermarket.wordpress.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.bican.wordpress.Page;
import net.bican.wordpress.PageDefinition;
import net.bican.wordpress.Wordpress;
import redstone.xmlrpc.XmlRpcFault;

import com.gipermarket.wordpress.domain.WPPost;
import com.gipermarket.wordpress.domain.dto.WPSiteDto;
import com.gipermarket.wordpress.service.IPoster;

/**
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class PosterImpl implements IPoster {

	/**
	 * 
	 * @param wpdto
	 *            WPSiteDto
	 * @param post
	 *            WPPost
	 * @return status of post
	 * @throws MalformedURLException
	 */
	public String post(WPSiteDto wpdto, WPPost post) throws MalformedURLException, XmlRpcFault {

		Wordpress wp = new Wordpress(wpdto.getUsername(), wpdto.getPassword(), wpdto.getXmlRpcUrl());

		List<Page> recentPosts = new ArrayList<Page>();
//		recentPosts = wp.getRecentPosts();
		System.out.println("Here are the ten recent posts:");
		for (Page page : recentPosts) {
			System.out.println(page.getPostid() + ":" + page.getTitle());
		}
		List<PageDefinition> pages = wp.getPageList();
		System.out.println("Here are the pages:");
		for (PageDefinition pageDefinition : pages) {
			System.out.println(pageDefinition.getPage_title());
		}
		System.out.println("Posting a test (draft) page from a previous page...");
		Page recentPost = wp.getRecentPosts(1).get(0);
		recentPost.setTitle("Test Page");
		recentPost.setDescription("Test description");
		String result = wp.newPost(recentPost, false);
		System.out.println("new post page id: " + result);
		System.out.println("\nThat's all for now.");

		return "posted";
	}

}
