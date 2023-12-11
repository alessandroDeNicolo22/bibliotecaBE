package com.bibliotecaBE.data.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FornitoreResponse {
	//	SEZIONE ATTRIBUTI

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String ragioneSociale;

    @JsonProperty
    private String indirizzo;

    @JsonProperty
    private String citta;

    @JsonProperty
    private String cap;

    @JsonProperty
    private String provincia;

    @JsonProperty
    private String partitaIva;

}
