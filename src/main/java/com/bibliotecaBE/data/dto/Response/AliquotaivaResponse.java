package com.bibliotecaBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliquotaivaResponse {
	//***Sezione Attributi***\\\

	@JsonProperty
	private Integer id;

	@JsonProperty
	private int aliquota;

	@JsonProperty
	private String descrizione;
}
