package com.europCarBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AreaResponse {

	//*********** SEZIONE ATTRIBUTI ************

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String codice;

	@JsonProperty
	private String area;

}
