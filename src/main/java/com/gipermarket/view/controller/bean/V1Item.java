package com.gipermarket.view.controller.bean;

import java.io.Serializable;

/**
 * Custom V1 object (Workitem)
 * 
 * @author dmitry.karpenko
 * 
 */
public class V1Item implements Serializable {
	private static final long serialVersionUID = 99315316548666825L;

	private String number;
	private String name;
	private String url;
	private Double estimate;
	private String creationDate;
	private String estimateChanged;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getEstimate() {
		return estimate;
	}

	public void setEstimate(Double estimate) {
		this.estimate = estimate;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getEstimateChanged() {
		return estimateChanged;
	}

	public void setEstimateChanged(String estimateChanged) {
		this.estimateChanged = estimateChanged;
	}
}
