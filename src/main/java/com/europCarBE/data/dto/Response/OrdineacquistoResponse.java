package com.europCarBE.data.dto.Response;

import com.europCarBE.data.entity.Fornitore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrdineacquistoResponse {
//	SEZIONE ATTRIBUTI

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Double importo;

    @JsonProperty
    private String ordineacquisto;

    @JsonProperty
    private Date data;

    @JsonProperty
    private Fornitore oFornitore;
}
