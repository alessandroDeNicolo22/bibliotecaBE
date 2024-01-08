package com.bibliotecaBE.data.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessoreRequest {

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
