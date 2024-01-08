package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RichiestaDiAcquistoRequest {
    //***Sezione Attributi***\\
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
