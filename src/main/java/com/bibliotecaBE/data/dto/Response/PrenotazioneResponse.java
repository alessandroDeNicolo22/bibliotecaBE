package com.bibliotecaBE.data.dto.Response;

import com.bibliotecaBE.data.entity.Libro;
import com.bibliotecaBE.data.entity.Prenotazione;
import com.bibliotecaBE.data.entity.Professore;
import com.bibliotecaBE.data.entity.RichiestaDiAcquisto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {
//	SEZIONE ATTRIBUTI

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Libro oLibro;

    @JsonProperty
    private Integer idRichiedente;

    @JsonProperty
    private String richiedente;

    @JsonProperty
    private Date data;

    @JsonProperty
    private String evasa;
}
