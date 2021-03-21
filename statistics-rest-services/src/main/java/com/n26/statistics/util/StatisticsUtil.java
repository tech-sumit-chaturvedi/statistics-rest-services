package com.n26.statistics.util;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * This class contains globally used methods.
 * 
 * @author Sumit.Chaturvedi
 * @since 2021-19-03 03:48
 *
 */
public class StatisticsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(StatisticsUtil.class);	

	/**
	 * Converts the given input string to Map.
	 * @param String
	 * @return Map
	 * @author Sumit.Chaturvedi
	 * @since 2021-19-03 03:48
	 */
	@SuppressWarnings({ "unchecked"})
	public static Map<String, Object> jsonToMap(String s) {
		try {
			logger.info("jsonToMap()-------> jsonToMap Method Invoked.");
			logger.info("jsonToMap()-------> Convert Json To Map.");
			return new ObjectMapper().readValue(s, Map.class);
		} catch (Exception e) {
			logger.error("Exception Occured while converting Json To Map", e);
			return null;
		}
	}
	
	/**
	 * Converts the requested Map to JSON.
	 * @param Map
	 * @return JSON String
	 * @author Sumit.Chaturvedi
	 * @since 2021-19-03 03:48
	 */
	
	public static String mapToJson(Map<String, Object> map) {
		try {
			logger.info("mapToJson()-------> mapToJson Method Invoked.");
			logger.info("mapToJson()-------> Convert Map To Json.");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(map);
		} catch (Exception e) {
			logger.error("Exception Occured While Converting Map to Json", e);
			// e.printStackTrace();
			return null;
		}

	}
	
	/*
	 * public static boolean isDigitOrNot(String amount) {
	 * if(amount[0].matches("[0-9.]*")) { // match a string containing digits or
	 * dots return null; }
	 * 
	 * }
	 */

}
