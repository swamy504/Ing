package com.mobiquity.controller;

import static com.mobiquity.utils.Uri.ING_BASE_URI;
import static com.mobiquity.utils.Uri.LOCATOR_ATMS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiquity.intergration.service.IngIntergrationService;
import com.mobiquity.model.ErrorResponse;
import com.mobiquity.model.IngResponse;

@RestController
@RequestMapping(ING_BASE_URI)
public class IngIntegrationController {

	@Autowired
	private IngIntergrationService ingIntergrationService;

	@GetMapping(LOCATOR_ATMS+"/{cityName}")
	public ResponseEntity<?> getAllAtms(@PathVariable("cityName") String cityName) throws Exception {
		List<IngResponse> list = null;
		ErrorResponse errorResponse = new ErrorResponse();
		if (null == cityName || cityName.trim().equals("") || cityName.trim().equals("null")) {
			errorResponse.setMessage("City name is madatory");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		try {
			
			list = ingIntergrationService.getAllAtms();
			if (null != list && list.size() > 0) {
				list =  list.stream().filter(res -> res != null && res.getAddress() != null
						&& cityName.equalsIgnoreCase(res.getAddress().getCity())).collect(Collectors.toList());
				return new ResponseEntity<List<IngResponse>>(list, HttpStatus.OK);
			}
		} catch (Exception ex) {
			errorResponse.setMessage(ex.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new ArrayList<IngResponse>() , HttpStatus.OK);
	}

}
