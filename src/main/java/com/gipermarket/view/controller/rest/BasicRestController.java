package com.gipermarket.view.controller.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.gipermarket.domain.AppSettings;
import com.gipermarket.exception.SubsystemConnectionException;
import com.gipermarket.util.JsonUtil;
import com.gipermarket.util.MessageUtil;

/**
 * 
 * A BasicRestController is a basic controller for rest services
 * 
 * @author sergey.evliukhin
 */
public class BasicRestController {
	private JsonUtil jsonUtil;

	private static final Logger log = Logger.getLogger(BasicRestController.class.getName());

	/**
	 * Method handles Exception and all subclasses and returns json serialized error
	 * 
	 * @param ex
	 *            - thrown exception instance
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(Exception ex) {
		log.severe(ex.getMessage());
		return new JsonError(MessageUtil.getMessage("message.default")).asModelAndView();
	}

	/**
	 * Method handles IllegalArgumentException and returns json serialized error
	 * 
	 * @param ex
	 *            - thrown exception instance
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
		String message = ex.getMessage();
		log.severe(message);
		return new JsonError(message).asModelAndView();
	}

	/**
	 * Method handles SubsystemConnectionException and returns json serialized error
	 * 
	 * @param ex
	 *            - thrown exception instance
	 * @return
	 */
	@ExceptionHandler(SubsystemConnectionException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleSubsystemConnectionException(SubsystemConnectionException ex) {
		String message = MessageUtil.getMessage("message.v1Connection.error", AppSettings.getInstance().getUrl());
		log.severe(ex.getMessage());
		return new JsonError(message).asModelAndView();
	}

	public static class JsonError {
		private final String message;

		public JsonError(String message) {
			this.message = message;
		}

		public ModelAndView asModelAndView() {
			MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
			Map<String, String> model = new HashMap<String, String>();
			model.put("error", message);
			return new ModelAndView(jsonView, model);
		}
	}

	public JsonUtil getJsonUtil() {
		return jsonUtil;
	}

	public void setJsonUtil(JsonUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
