package com.europCarBE.data.dto.Response;

import com.europCarBE.data.entity.Aliquotaiva;
import com.europCarBE.data.entity.Fatturapassiva;
import com.europCarBE.data.entity.Preventivo;
import com.europCarBE.data.entity.Spesainvestimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatturadettaglioResponse {
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
