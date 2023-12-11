package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Autore;
import com.bibliotecaBE.data.entity.Libro;
import com.bibliotecaBE.data.entity.Prestito;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FatturadettaglioRequest {
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
