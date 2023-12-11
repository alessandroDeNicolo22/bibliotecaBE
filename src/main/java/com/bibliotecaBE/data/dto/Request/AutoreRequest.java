package com.bibliotecaBE.data.dto.Request;

import java.util.List;

import com.bibliotecaBE.data.entity.Copia;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutoreRequest {
	//***Sezione Attributi***\\\

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String cognome;

	@JsonProperty
	private String nome;
}
