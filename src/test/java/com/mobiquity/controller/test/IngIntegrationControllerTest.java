package com.mobiquity.controller.test;

import static com.mobiquity.utils.Uri.ING_BASE_URI;
import static com.mobiquity.utils.Uri.LOCATOR_ATMS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.controller.IngIntegrationController;
import com.mobiquity.model.IngResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(IngIntegrationController.class)
public class IngIntegrationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllAtms() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ING_BASE_URI + LOCATOR_ATMS + "/Maastricht")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<IngResponse> response = objectMapper.readValue(json, new TypeReference<List<IngResponse>>() { });
		assertNotNull(response);
		assertEquals("Maastricht", response.get(0).getAddress().getCity());
		}

	@Test
	void testGetAllAtmsThrowException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ING_BASE_URI + LOCATOR_ATMS + "/null")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().json("{'message':'City name is madatory'}"));
	}
	
	@Test
	void testGetAllAtmsNoCityMatches() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ING_BASE_URI + LOCATOR_ATMS + "/test")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

}
