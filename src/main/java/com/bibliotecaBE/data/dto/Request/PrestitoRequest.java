package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Genere;
import com.bibliotecaBE.data.entity.Libro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PrestitoRequest {

    //********** SEZIONE ATTRIBUTI ***********

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Integer idDestinatario;

    @JsonProperty
    private Libro oLibro;

    @JsonProperty
    private String destinatario;

    @JsonProperty
    private Date dataInizio;

    @JsonProperty
    private Date dataFine;

    @JsonProperty
    private Date dataRestituzione;

}
