package com.europCarBE.data.dto.Response;

import com.europCarBE.data.entity.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SottocategoriaResponse {
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
