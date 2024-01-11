package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Copia;
import com.bibliotecaBE.data.entity.Genere;
import com.bibliotecaBE.data.entity.Libro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestitoResponse {

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
