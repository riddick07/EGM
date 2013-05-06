package com.gipermarket.domain;

public class AppSettings {
	
	private static AppSettings instance;
	private String url;
	private boolean productionMode = System.getProperty("devenv") == null;

	AppSettings() {
	}

	public static AppSettings getInstance() {
		if (instance == null) {
			instance = new AppSettings();
		}
		
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isProductionMode() {
		return productionMode;
	}
}
