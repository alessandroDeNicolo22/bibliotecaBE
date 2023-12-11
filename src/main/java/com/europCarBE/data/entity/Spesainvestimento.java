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
@Table(name ="Spesainvestimento")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Spesainvestimento {

	//Sezione Attributi
	@Id
	@Column(name = "IDSpesainvestimento")
	@SequenceGenerator(name = "SEQCHIAVESPESAINVESTIMENTO", sequenceName = "SEQCHIAVESPESAINVESTIMENTO", allocationSize = 1)
	@GeneratedValue(generator = "SEQCHIAVESPESAINVESTIMENTO", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "Spesainvestimento")
	private String spesainvestimento;

	@ManyToOne
	@JoinColumn(name = "IDSottocategoria")
	private Sottocategoria oSottocategoria;
}
