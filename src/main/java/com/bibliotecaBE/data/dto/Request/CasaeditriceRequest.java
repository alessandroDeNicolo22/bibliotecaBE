package com.bibliotecaBE.data.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CasaeditriceRequest {
	
	//*********** SEZIONE ATTRIBUTI ************

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String nome;

}
