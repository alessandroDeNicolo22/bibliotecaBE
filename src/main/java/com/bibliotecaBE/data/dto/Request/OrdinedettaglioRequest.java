package com.bibliotecaBE.data.dto.Request;

import com.bibliotecaBE.data.entity.Prenotazione;
import com.bibliotecaBE.data.entity.Professore;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdinedettaglioRequest {
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
