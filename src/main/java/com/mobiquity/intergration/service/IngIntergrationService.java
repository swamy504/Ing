package com.mobiquity.intergration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobiquity.model.IngResponse;

@Service
public interface IngIntergrationService {

	List<IngResponse> getAllAtms() throws Exception;

}
