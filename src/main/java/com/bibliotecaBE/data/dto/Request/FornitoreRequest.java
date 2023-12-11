package com.bibliotecaBE.data.dto.Request;

import java.util.List;

import com.bibliotecaBE.data.entity.Libro;
import com.bibliotecaBE.data.entity.Prenotazione;
import com.bibliotecaBE.data.entity.Prestito;
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
	private List<Libro> elencoFatture;

	@JsonProperty
	private List<Prestito> elencoPreventivi;

	@JsonProperty
	private List<Prenotazione> elencoOrdini;
}
