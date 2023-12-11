package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Genere;
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
    private Genere oGenere;
}
