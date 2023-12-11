package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Genere;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
public class OrdineacquistoRequest {
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
