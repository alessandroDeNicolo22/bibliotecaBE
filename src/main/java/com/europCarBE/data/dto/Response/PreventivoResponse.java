package com.europCarBE.data.dto.Response;

import com.europCarBE.data.entity.Fornitore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreventivoResponse {

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
