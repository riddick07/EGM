package com.gipermarket.wordpress.service.impl;

import java.net.MalformedURLException;

import net.bican.wordpress.Wordpress;

import com.gipermarket.wordpress.domain.WPPost;
import com.gipermarket.wordpress.domain.dto.WPSiteDto;
import com.gipermarket.wordpress.service.IPoster;

public class PosterImpl implements IPoster {

	/**
	 * 
	 * @param wpdto WPSiteDto
	 * @param post WPPost
	 * @return status of post
	 * @throws MalformedURLException
	 */
	public String post(WPSiteDto wpdto, WPPost post) throws MalformedURLException {
		
		Wordpress wp = new Wordpress(wpdto.getUsername(), wpdto.getPassword(), wpdto.getXmlRpcUrl());

		return "posted";
	}

}
