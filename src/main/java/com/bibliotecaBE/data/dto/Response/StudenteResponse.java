package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Casaeditrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudenteResponse {
    //***Sezione Attributi***\\
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
