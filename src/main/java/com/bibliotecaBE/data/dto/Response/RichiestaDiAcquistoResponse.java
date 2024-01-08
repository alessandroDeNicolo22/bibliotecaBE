package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RichiestaDiAcquistoResponse {

    //sezione Attributi
    @JsonProperty
    private Integer id;

    @JsonProperty
    private Professore oProfessore;

    @JsonProperty
    private Libro oLibro;

    @JsonProperty
    private Autore oAutore;

    @JsonProperty
    private String titolo;

    @JsonProperty
    private Genere oGenere;

    @JsonProperty
    private String stato;


}
