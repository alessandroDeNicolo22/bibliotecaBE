package com.bibliotecaBE.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

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
	@JoinColumn(name="IDAutore")
	private Autore oAutore;

	@ManyToOne
	@JoinColumn(name="IDGenere")
	private Genere oGenere;

	@ManyToOne
	@JoinColumn(name="IDCasaeditrice")
	private Casaeditrice oCasaeditrice;

	@Column(name="Titolo")
	private String titolo;

}
