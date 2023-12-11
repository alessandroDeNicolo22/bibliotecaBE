package com.europCarBE.data.entity;

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
@Table(name="Preventivo")
public class Preventivo {
	//Sezione attributi
	@Id
	@Column(name = "IDPreventivo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEPREVENTIVO")
	@SequenceGenerator(name = "SEQCHIAVEPREVENTIVO", sequenceName = "SEQCHIAVEPREVENTIVO", allocationSize = 1)
	private Integer id;

	@Column(name="Codice")
	private String codice;

	@Column(name="Preventivo")
	private String preventivo;

	@ManyToOne
	@JoinColumn(name="IDFornitore")
	private Fornitore oFornitore;

	@Column(name="Importo")
	private Double importo;

	@Column(name ="Data")
	private Date data;
}
