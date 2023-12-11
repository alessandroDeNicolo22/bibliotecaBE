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
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Copia")
public class Copia {
	//***Sezione Attributi***\\
		@Column(name="IDCopia")
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVECOPIA")
		@SequenceGenerator(name = "SEQCHIAVECOPIA", sequenceName = "SEQCHIAVECOPIA", allocationSize = 1)
		private Integer id;

		@ManyToOne
		@JoinColumn(name="IDLibro")
		private Libro oLibro;

		@JoinColumn(name="Stato")
		private String stato;

		@JoinColumn(name="Datadiacquisto")
		private Date date;

		@JoinColumn(name="Seriale")
		private String seriale;

}
