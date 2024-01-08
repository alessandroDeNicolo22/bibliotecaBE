package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Autore;
import com.bibliotecaBE.data.entity.Libro;
import com.bibliotecaBE.data.entity.Prestito;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CopiaRequest {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String stato;

    @JsonProperty
    private Libro oLibro;

    @JsonProperty
    private Date dataDiAcquisto;

    @JsonProperty
    private Integer seriale;
}
