package com.n26.statistics.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

/**
 * This class contains globally used methods.
 * 
 * @author Sumit.Chaturvedi
 * @since 2021-19-03 03:48
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class StatisticsUtilTest extends TestCase {
	
	@InjectMocks
	StatisticsUtil statisticsUtil;
	
	@Before
   public void Setup() {
		MockitoAnnotations.initMocks(this);
	   
   }
	
	@Test
	public void testJsonToMap() throws JsonMappingException, JsonProcessingException {
		String value =  new ObjectMapper().writeValueAsString(new HashMap<>());
		StatisticsUtil.jsonToMap(value);		
	}
	
	
	@Test
	public void testMapToJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		objectMapper.writeValueAsString(map);
		StatisticsUtil.mapToJson(map);
		}


}
