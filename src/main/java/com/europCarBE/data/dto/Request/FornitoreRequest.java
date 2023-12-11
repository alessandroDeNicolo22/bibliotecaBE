package com.europCarBE.data.dto.Request;

import java.util.List;

import com.europCarBE.data.entity.Fatturapassiva;
import com.europCarBE.data.entity.Ordineacquisto;
import com.europCarBE.data.entity.Preventivo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FornitoreRequest {
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

    @JsonProperty
	private List<Fatturapassiva> elencoFatture;

	@JsonProperty
	private List<Preventivo> elencoPreventivi;

	@JsonProperty
	private List<Ordineacquisto> elencoOrdini;
}
