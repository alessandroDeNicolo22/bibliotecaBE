package com.bibliotecaBE.data.dto.Request;

import java.util.List;

import com.bibliotecaBE.data.entity.Copia;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AliquotaivaRequest {
	//***Sezione Attributi***\\\

	@JsonProperty
	private Integer id;

	@JsonProperty
	private int aliquota;

	@JsonProperty
	private String descrizione;
	
	@JsonProperty
	private List<Copia> elencoDettagliFattura;
}
