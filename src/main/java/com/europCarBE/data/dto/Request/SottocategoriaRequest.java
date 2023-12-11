package com.europCarBE.data.dto.Request;

import com.europCarBE.data.entity.Area;
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
    private Area oArea;
}
