package com.europCarBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="Fatturapassiva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fatturapassiva {
	@Column(name="IDFatturapassiva")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEFATTURAPASSIVA")
	@SequenceGenerator(name = "SEQCHIAVEFATTURAPASSIVA", sequenceName = "SEQCHIAVEFATTURAPASSIVA", allocationSize = 1)
	private Integer id;
	
	@Column(name="Data")
	private Date data;
	
	@Column(name="Numero")
	private String numero;
	
	@Column(name="Descrizione")
	private String descrizione;
	
	@ManyToOne
	@JoinColumn(name="IDFornitore")
	private Fornitore oFornitore;
}
