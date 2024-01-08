package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class GenereRequest {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String nome;

}
