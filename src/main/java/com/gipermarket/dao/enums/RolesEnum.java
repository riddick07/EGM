package com.gipermarket.dao.enums;

import com.gipermarket.util.MessageUtil;

public enum RolesEnum {

	ADMIN("Administrator"), DEVELOPER("Developer"), CUSTOMER("Customer"), GUEST("Guest"), SELLER("Seller"), BUYER("Buyer");

	private String name;

	RolesEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static RolesEnum getName(String name) {
		RolesEnum[] values = values();
		for (RolesEnum report : values) {
			if (report.getName().equals(name)) {
				return report;
			}
		}
		throw new IllegalArgumentException(MessageUtil.getMessage("message.chartIsNotSupported", name));
	}
}
