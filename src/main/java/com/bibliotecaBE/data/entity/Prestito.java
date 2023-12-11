package com.bibliotecaBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Prestito")
public class Prestito {
	//Sezione attributi
	@Id
	@Column(name = "IDPrestito")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEPRESTITO")
	@SequenceGenerator(name = "SEQCHIAVEPRESTITO", sequenceName = "SEQCHIAVEPRESTITO", allocationSize = 1)
	private Integer id;

	@Column(name="IDDestinatario")
	private Integer idDestinatario;

	@ManyToOne
	@Column(name="IDLibro")
	private Libro oLibro;

	@JoinColumn(name="Destinatario")
	private String destinatario;

	@Column(name="Importo")
	private Double importo;

	@Column(name ="Datainizio")
	private Date dataInizio;

	@Column(name ="DataFine")
	private Date dataFine;

	@Column(name ="DataiRestituzione")
	private Date dataRestituzione;
}
