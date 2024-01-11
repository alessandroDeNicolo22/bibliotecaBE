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
	@JoinColumn(name = "IDLibro")
	private Copia oCopia;

	@Column(name="Destinatario")
	private String destinatario;

	@Column(name ="Datainizio")
	private Date dataInizio;

	@Column(name ="Datafine")
	private Date dataFine;

	@Column(name ="Datarestituzione")
	private Date dataRestituzione;
}
