package com.europCarBE.data.dto.Response;

import com.europCarBE.data.entity.Fatturapassiva;
import com.europCarBE.data.entity.Ordineacquisto;
import com.europCarBE.data.entity.Preventivo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
