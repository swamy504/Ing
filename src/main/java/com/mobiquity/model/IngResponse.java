package com.mobiquity.model;

import java.io.Serializable;
import java.util.List;

public class IngResponse implements Serializable {

	private static final long serialVersionUID = 4379943418307470698L;
	private Address address;
	private Long distance;
	private List<OpeningHours> openingHours;
	private String functionality;
	private String type;

	public IngResponse() {
		super();
	}

	public IngResponse(Address address, Long distance, List<OpeningHours> openingHours, String functionality,
			String type) {
		super();
		this.address = address;
		this.distance = distance;
		this.openingHours = openingHours;
		this.functionality = functionality;
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public List<OpeningHours> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHours> openingHours) {
		this.openingHours = openingHours;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
