package com.bibliotecaBE.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@Entity
@Table(name="Genere")
@RequiredArgsConstructor
public class Genere {
	//***Sezione Attributi***\\
	@Id
	@Column(name = "IDGenere")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEGENERE")
	@SequenceGenerator(name = "SEQCHIAVEGENERE", sequenceName = "SEQCHIAVEGENERE", allocationSize = 1)
	private Integer id;

	@Column(name = "Nome")
	private String nome;

}