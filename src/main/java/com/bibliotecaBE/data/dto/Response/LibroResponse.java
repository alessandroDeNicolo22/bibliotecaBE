package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Autore;
import com.bibliotecaBE.data.entity.Casaeditrice;
import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LibroResponse {
//	SEZIONE ATTRIBUTI

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Autore oAutore;

    @JsonProperty
    private Genere oGenere;

    @JsonProperty
    private Casaeditrice oCasaeditrice;

    @JsonProperty
    private String titolo;
}
