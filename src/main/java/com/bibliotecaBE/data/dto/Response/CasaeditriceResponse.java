package com.bibliotecaBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CasaeditriceResponse {

	//*********** SEZIONE ATTRIBUTI ************

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String nome;

}
