package com.europCarBE.data.dto.Request;

import com.europCarBE.data.entity.Ordineacquisto;
import com.europCarBE.data.entity.Progetto;
import com.europCarBE.data.entity.Spesainvestimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdinedettaglioRequest {
//	SEZIONE ATTRIBUTI

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Spesainvestimento oSpesaInvestimento;

    @JsonProperty
    private Progetto oProgetto;

    @JsonProperty
    private Ordineacquisto oOrdineAcquisto;

    @JsonProperty
    private Float importo;

    @JsonProperty
    private Integer quantita;
}
