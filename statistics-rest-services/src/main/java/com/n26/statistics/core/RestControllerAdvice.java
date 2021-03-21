
package com.n26.statistics.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.n26.statistics.util.StatisticsUtil;

/**
 * This class is used to catch the exception
 * @author Sumit.Chaturvedi
 * @since 2021-21-03 23:24
 */
@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
	

	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity handleAll(Exception exception, WebRequest request) {
	Map<String, Object> res = StatisticsUtil.jsonToMap(exception.getMessage());
		 exception.getMessage();
		if(res.get("errors") != null) {
			res.put("data", new HashMap<>() {{
				put("errors",res.get("res"));
			}});
			res.remove("errors");
		}
		return new ResponseEntity(res,HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exception(HttpServletRequest request, Exception exception) throws Exception {
		// HttpRequestMethodNotSupportedException exception.printStackTrace();
		if (exception instanceof HttpRequestMethodNotSupportedException)
			throw exception;
		Map<String, Object> res = StatisticsUtil.jsonToMap(exception.getMessage());
		if (res.get("errors") != null) {
			res.put("data", new HashMap<>() {{
					put("errors", res.get("res"));
					put("success", false);
				}});
			res.remove("errors");
		}
		return new ResponseEntity(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
}
