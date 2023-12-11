package com.europCarBE.data.dto.Request;

import com.europCarBE.data.entity.Fornitore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class PreventivoRequest {

    //********** SEZIONE ATTRIBUTI ***********

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String codice;

    @JsonProperty
    private String preventivo;

    @JsonProperty
    private Fornitore oFornitore;

    @JsonProperty
    private Double importo;

    @JsonProperty
    private Date data;

}
