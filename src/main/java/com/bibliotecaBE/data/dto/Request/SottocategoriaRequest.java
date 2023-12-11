package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Casaeditrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SottocategoriaRequest {
    //***Sezione Attributi***\\
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String codice;

    @JsonProperty
    private String sottocategoria;

    @JsonProperty
    private float budget;

    @JsonProperty
    private float budgetSpeso;

    @JsonProperty
    private Casaeditrice oCasaeditrice;
}
