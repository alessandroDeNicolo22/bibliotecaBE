package com.europCarBE.data.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@Entity
@Table(name="Fornitore")
@RequiredArgsConstructor
public class Fornitore {
	//***Sezione Attributi***\\
	@Id
    @Column(name = "IDFornitore")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEFORNITORE")
    @SequenceGenerator(name = "SEQCHIAVEFORNITORE", sequenceName = "SEQCHIAVEFORNITORE", allocationSize = 1)
    private Integer id;

	@Column(name="Ragionesociale")
	private String ragioneSociale;

	@Column(name="Indirizzo")
	private String indirizzo;

	@Column(name="Citta")
	private String citta;

	@Column(name="Cap")
	private String cap;

	@Column(name="Provincia")
	private String provincia;

	@Column(name="Partitaiva")
	private String partitaIva;

//	@JsonIgnore
//	@OneToMany(mappedBy = "oFornitore")
//	private List<Fatturapassiva> elencoFatture;
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "oFornitore")
//	private List<Preventivo> elencoPreventivi;
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "oFornitore")
//	private List<Ordineacquisto> elencoOrdini;
}
//L'errore "Infinite recursion (StackOverflowError)" si verifica quando c'è un ciclo infinito di riferimenti incrociati tra le entità all'interno del processo di serializzazione JSON. Questo accade quando hai associazioni bidirezionali
//		tra le tue entità e il framework Jackson (usato da Spring per la serializzazione JSON) cerca di serializzarle.
//		Nel tuo caso, sembra che tu abbia un'associazione bidirezionale tra l'entità Ordineacquisto e l'entità Fornitore attraverso la proprietà elencoOrdini e ofornitore.
//		Questo sta causando un ciclo infinito durante la serializzazione JSON, poiché Jackson cerca di serializzare l'ordine dell'acquisto, che a sua volta richiama la serializzazione del fornitore,
//		che richiama di nuovo la serializzazione degli ordini, e così via.
//		Per risolvere questo problema, puoi considerare le seguenti opzioni:
//
//		1)Rimuovere Associazioni Bidirezionali: Valuta se è davvero necessario avere associazioni bidirezionali tra le tue entità.
//		Potresti essere in grado di rimuovere una delle associazioni o trasformarla in unidirezionale, in modo che Jackson non incappi nel ciclo infinito.
//
//		2)Usare Annotazioni di Jackson: Utilizza annotazioni fornite da Jackson per gestire la serializzazione in modo personalizzato.
//		Ad esempio, puoi utilizzare l'annotazione @JsonIgnore su una delle proprietà per escluderla dalla serializzazione.