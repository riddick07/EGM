package com.gipermarket.util;

import java.util.HashMap;
import java.util.logging.Logger;

public class Time {
	private static final Logger log = Logger.getLogger(Time.class.getName());
	private static HashMap<String, Long> timers = new HashMap<String, Long>();
	private static String DEFAULT_TIMER = "default";

	public static void start(String group) {
		if (timers.containsKey(group)) {
			timers.clear();
			throw new IllegalArgumentException(MessageUtil.getMessage("message.timerAlreadyStarted", group));
		}
		timers.put(group, System.currentTimeMillis());
	}

	public static void end(String group) {
		Long end = timers.get(group);
		if (end == null) {
			timers.clear();
			throw new IllegalArgumentException(MessageUtil.getMessage("message.timerNeverStarted", group));
		}
		timers.remove(group);

		log.info("[" + group + "] Time: " + (System.currentTimeMillis() - end));
	}

	public static void start() {
		start(DEFAULT_TIMER);
	}

	public static void end() {
		end(DEFAULT_TIMER);
	}
}
