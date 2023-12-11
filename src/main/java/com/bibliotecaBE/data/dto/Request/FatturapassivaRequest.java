package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class FatturapassivaRequest {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Date data;

    @JsonProperty
    private String numero;

    @JsonProperty
    private String descrizione;

   @JsonProperty
    private Genere oGenere;

}
