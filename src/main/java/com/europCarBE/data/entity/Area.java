package com.europCarBE.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Area")
public class Area {

	//************* SEZIONE ATTRIBUTI **********

	@Column(name = "IDArea")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEAREA")
	@SequenceGenerator(name = "SEQCHIAVEAREA", sequenceName = "SEQCHIAVEAREA", allocationSize = 1)
	private Integer id;

	@Column(name = "Codice")
	private String codice;

	@Column(name = "Area")
	private String area;

}

