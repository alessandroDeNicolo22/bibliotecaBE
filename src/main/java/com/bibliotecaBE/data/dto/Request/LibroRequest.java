package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Autore;
import com.bibliotecaBE.data.entity.Casaeditrice;
import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
public class LibroRequest {
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
