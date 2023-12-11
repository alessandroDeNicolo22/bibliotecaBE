package com.bibliotecaBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="Libro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
	@Column(name="IDLibro")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVELIBRO")
	@SequenceGenerator(name = "SEQCHIAVELIBRO", sequenceName = "SEQCHIAVELIBRO", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@Column(name="IDAutore")
	private Autore oAutore;

	@ManyToOne
	@Column(name="IDGenere")
	private Genere oGenere;

	@ManyToOne
	@Column(name="IDCasaeditrice")
	private Casaeditrice oCasaeditrice;

	@JoinColumn(name="Titolo")
	private String titolo;
}
