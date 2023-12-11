package com.bibliotecaBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "Prenotazione")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {
//	SEZIONE ATTRIBUTI
	
	@Id
	@Column(name = "IDPrenotazione")
	@GeneratedValue(generator = "SEQCHIAVEPRENOTAZIONE", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "SEQCHIAVEPRENOTAZIONE", sequenceName = "SEQCHIAVEPRENOTAZIONE", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@Column(name = "IDLibro")
	private Libro oLibro;
	
	@Column(name = "IDRichiedente")
	private Integer idRichiedente;
	
	@Column(name = "Richiedente")
	private String richiedente;

	@JoinColumn(name = "Data")
	private Date data;

	@JoinColumn(name = "Evasa")
	private String evasa;
}
