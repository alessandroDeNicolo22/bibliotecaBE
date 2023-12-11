package com.europCarBE.data.entity;

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
@Table(name = "Ordineacquisto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ordineacquisto {
//	SEZIONE ATTRIBUTI
	
	@Id
	@Column(name = "IDOrdineacquisto")
	@GeneratedValue(generator = "SEQCHIAVEORDINEACQUISTO", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "SEQCHIAVEORDINEACQUISTO", sequenceName = "SEQCHIAVEORDINEACQUISTO", allocationSize = 1)
	private Integer id;
	
	@Column(name = "Importo")
	private Double importo;
	
	@Column(name = "Ordineacquisto")
	private String ordineAcquisto;
	
	@Column(name = "Data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "IDFornitore")
	private Fornitore oFornitore;	
}
