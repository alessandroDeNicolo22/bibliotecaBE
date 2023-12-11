package com.europCarBE.data.dto.Request;

import com.europCarBE.data.entity.Aliquotaiva;
import com.europCarBE.data.entity.Fatturapassiva;
import com.europCarBE.data.entity.Preventivo;
import com.europCarBE.data.entity.Spesainvestimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
public class FatturadettaglioRequest {
    @JsonProperty
    private Integer id;

   @JsonProperty
    private Aliquotaiva oAliquotaiva;

   @JsonProperty
    private Fatturapassiva oFatturapassiva;

   @JsonProperty
    private Preventivo oPreventivo;

    @JsonProperty
    private Spesainvestimento oSpesainvestimento;

    @JsonProperty
    private String dettaglioFattura;

    @JsonProperty
    private Double importo;
}
