package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PreventivoRequest {

    //********** SEZIONE ATTRIBUTI ***********

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String codice;

    @JsonProperty
    private String preventivo;

    @JsonProperty
    private Genere oGenere;

    @JsonProperty
    private Double importo;

    @JsonProperty
    private Date data;

}
