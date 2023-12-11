package com.bibliotecaBE.data.entity;

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
@Table(name ="RichiestaDiAcquisto")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RichiestaDiAcquisto {

	//Sezione Attributi
	@Id
	@Column(name = "IDRichiestadiacquisto")
	@SequenceGenerator(name = "SEQCHIAVERICHIESTADIACQUISTO", sequenceName = "SEQCHIAVERICHIESTADIACQUISTO", allocationSize = 1)
	@GeneratedValue(generator = "SEQCHIAVERICHIESTADIACQUISTO", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "Spesainvestimento")
	private String spesainvestimento;

	@ManyToOne
	@JoinColumn(name = "IDProfessore")
	private Professore oProfessore;

	@Column(name = "Titolo")
	private String titolo;

	@ManyToOne
	@JoinColumn(name = "IDLibro")
	private Libro oLibro;

	@ManyToOne
	@Column(name = "IDAutore")
	private Autore oAutore;

	@ManyToOne
	@JoinColumn(name = "IDGenere")
	private Genere oGenere;

	@Column(name = "Stato")
	private String stato;
}