package com.gipermarket.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageUtil {
	/**
	 * get message by it's key from messages.properties. If key is null or does not exist, message for message.default wiil be returned
	 * 
	 * @param messageKey
	 *            - key of message
	 * @return String message
	 */
	public static String getMessage(String messageKey) {
		if (messageKey == null) {
			return (String) ResourceBundle.getBundle("messages").getObject("message.default");
		}

		String message = (String) ResourceBundle.getBundle("messages").getObject(messageKey);
		if (message == null) {
			return (String) ResourceBundle.getBundle("messages").getObject("message.default");
		}

		return message;
	}

	/**
	 * get message by it's key from messages.properties and replace placeholders with values. If key is null or does not exist, message for
	 * message.default wiil be returned
	 * 
	 * @param messageKey
	 *            - key of message
	 * @param params
	 *            - vararg with strings, which should replace placeholders
	 * @return String message
	 */
	public static String getMessage(String messageKey, String... params) {
		if (messageKey == null) {
			return (String) ResourceBundle.getBundle("messages").getObject("message.default");
		}

		String message = (String) ResourceBundle.getBundle("messages").getObject(messageKey);
		if (message == null) {
			return (String) ResourceBundle.getBundle("messages").getObject("message.default");
		}

		MessageFormat formatter = new MessageFormat("");
		formatter.applyPattern(message);
		return formatter.format(params);
	}
}
