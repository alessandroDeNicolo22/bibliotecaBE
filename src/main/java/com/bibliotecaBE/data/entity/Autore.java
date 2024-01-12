package com.bibliotecaBE.data.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name="Autore")
@Data
//Questa annotazione genera automaticamente metodi come toString(), equals(), hashCode(), nonché i getter e i setter per tutti i campi della classe.
//In pratica, questa annotazione semplifica la creazione di classi con i metodi comuni di utilità.
@AllArgsConstructor
//Questa annotazione genera un costruttore che accetta tutti i campi della classe come parametri. 
//In altre parole, crea un costruttore che inizializza tutti i campi della classe utilizzando i valori passati come argomenti al costruttore.
@RequiredArgsConstructor
//Questo costruttore viene utilizzato per creare istanze della classe garantendo che i campi essenziali siano inizializzati all'interno del costruttore.
public class Autore {
	//***Sezione Attributi***\\\

	@Column(name="IDAutore")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEAUTORE")
	@SequenceGenerator(name = "SEQCHIAVEAUTORE", sequenceName = "SEQCHIAVEAUTORE", allocationSize = 1)
	private Integer id;

	@Column(name="Cognome")
	private String cognome;

	@Column(name="Nome")
	private String nome;

	private String nomeCognome;
	public String getnomeCognome(){
        return nome + " " + cognome;
	}

}
