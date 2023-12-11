package com.bibliotecaBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgettoResponse {

	//****Sezione Attributi****

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String codice;

	@JsonProperty
	private String progetto;

}
