package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Autore;
import com.bibliotecaBE.data.entity.Libro;
import com.bibliotecaBE.data.entity.Prestito;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatturadettaglioResponse {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private Autore oAutore;

    @JsonProperty
    private Libro oLibro;

    @JsonProperty
    private Prestito oPrestito;

    @JsonProperty
    private RichiestaDiAcquisto oRichiestaDiAcquisto;

    @JsonProperty
    private String dettaglioFattura;

    @JsonProperty
    private Double importo;
}
