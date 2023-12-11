package com.europCarBE.data.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgettoRequest {

	//****Sezione Attributi****

	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String codice;
	
	@JsonProperty
	private String progetto;

}
