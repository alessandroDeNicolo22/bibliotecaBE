package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Studente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SpesainvestimentoRequest {
    //sezione Attributi
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String spesainvestimento;
    @JsonProperty
    private Studente oStudente;
}
