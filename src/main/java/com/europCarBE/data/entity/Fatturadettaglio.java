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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Fatturapassivadettaglio")
public class Fatturadettaglio {
	//***Sezione Attributi***\\
		@Column(name="IDDettagliofattura")
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEFATTURAPASSIVADETTAGLIO")
		@SequenceGenerator(name = "SEQCHIAVEFATTURAPASSIVADETTAGLIO", sequenceName = "SEQCHIAVEFATTURAPASSIVADETTAGLIO", allocationSize = 1)
		private Integer id;

		@ManyToOne
		@JoinColumn(name="IDAliquotaiva")
		private Aliquotaiva oAliquotaiva;

		@ManyToOne
		@JoinColumn(name="IDFatturapassiva")
		private Fatturapassiva oFatturapassiva;
		
		@ManyToOne
		@JoinColumn(name="IDPreventivo")
		private Preventivo oPreventivo;

		@ManyToOne
		@JoinColumn(name="IDSpesainvestimento")
		private Spesainvestimento oSpesainvestimento;

		
		@Column(name="Dettagliofattura")
		private String dettaglioFattura;

		@Column(name="Importo")
		private Double importo;
}
