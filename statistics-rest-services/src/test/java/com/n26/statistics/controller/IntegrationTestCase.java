package com.n26.statistics.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.n26.statistics.service.ServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
@ContextConfiguration(classes = Controller.class)
public class IntegrationTestCase {
	
	@MockBean
	private ServiceImpl serviceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void testSave() throws Exception {
		String json = "{\"amount\":\"44\",\"timestamp\":\"2021-03-21T11:02:27.320371100Z[UTC]\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions").content(json)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/statistics")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	
	
	
}
		

	
