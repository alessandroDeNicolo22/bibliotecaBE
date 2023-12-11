package com.europCarBE.data.dto.Request;

import com.europCarBE.data.entity.Sottocategoria;
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
    private Sottocategoria oSottocategoria;
}
