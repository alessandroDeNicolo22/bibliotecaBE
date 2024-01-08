package com.bibliotecaBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessoreResponse {

	//****Sezione Attributi****

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String cognome;

	@JsonProperty
	private String nome;

	@JsonProperty
	private Integer matricola;

}
