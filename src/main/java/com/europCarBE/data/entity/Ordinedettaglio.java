package com.europCarBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "Ordinediacquistodettaglio")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Ordinedettaglio {
	//	SEZIONE ATTRIBUTI

	@Id
	@Column(name = "IDOrdinediacquistodettaglio")
	@GeneratedValue(generator = "SEQCHIAVEORDINEDIACQUISTODETTAGLIO", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "SEQCHIAVEORDINEDIACQUISTODETTAGLIO", sequenceName = "SEQCHIAVEORDINEDIACQUISTODETTAGLIO", allocationSize = 1)
	private Integer id;

	@JoinColumn(name = "IDSpesainvestimento")
	@ManyToOne
	private Spesainvestimento oSpesaInvestimento;

	@JoinColumn(name = "IDProgetto")
	@ManyToOne
	private Progetto oProgetto;

	@JoinColumn(name = "IDOrdineacquisto")
	@ManyToOne
	private Ordineacquisto oOrdineAcquisto;

	@Column(name = "Importo")
	private Float importo;

	@Column(name = "Quantita")
	private Integer quantita;
}
