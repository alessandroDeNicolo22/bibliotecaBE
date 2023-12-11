package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Prenotazione;
import com.bibliotecaBE.data.entity.Professore;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdinedettaglioResponse {
//	SEZIONE ATTRIBUTI

    @JsonProperty
    private Integer id;

    @JsonProperty
    private RichiestaDiAcquisto oSpesaInvestimento;

    @JsonProperty
    private Professore oProfessore;

    @JsonProperty
    private Prenotazione oOrdineAcquisto;

    @JsonProperty
    private Float importo;

    @JsonProperty
    private Integer quantita;
}
