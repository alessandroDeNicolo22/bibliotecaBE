package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatturapassivaResponse {
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
