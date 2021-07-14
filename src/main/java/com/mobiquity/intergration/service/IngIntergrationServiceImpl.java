package com.mobiquity.intergration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.model.IngResponse;

public class IngIntergrationServiceImpl implements IngIntergrationService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${ing.url}")
	private String ingUrl;

	@Override
	public List<IngResponse> getAllAtms() throws Exception {
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(ingUrl, String.class);
			String res = response.getBody();
			// NOTE:  here the Json response is not valid(not well formed) - so I'm removing first 5 char's from the response -  )]}',
			String jsonString = res.subSequence(5, res.length()).toString();
			return convertJsonToObject(jsonString);
		} catch (Exception ex) {
          throw new Exception(ex.getMessage());
		}
	}

	private List<IngResponse> convertJsonToObject(String jsonString) throws Exception {
		return objectMapper.readValue(jsonString, new TypeReference<List<IngResponse>>() { });
	}

}
