package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Studente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StudenteRequest {
    //sezione Attributi
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String cognome;

    @JsonProperty
    private String nome;

    @JsonProperty
    private Integer matricola;

    @JsonProperty
    private Date dataDiNascita;

    @JsonProperty
    private String indirizzo;

    @JsonProperty
    private String comune;

    @JsonProperty
    private String provincia;

    @JsonProperty
    private String nazione;

    @JsonProperty
    private Integer telefono;
}
