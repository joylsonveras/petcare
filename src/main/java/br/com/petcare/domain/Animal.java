package br.com.petcare.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.petcare.domain.enumeration.Especie;

@Entity
public class Animal {
	 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Especie especie;
	private String raca;
	private LocalDate dataNascimento;
	@ManyToOne
	@JoinColumn(name="tutor")
	private Tutor tutor;
	
	public Animal() {
	}
	
	public Animal(LocalDate dataNascimento, String especie, String nome, String raca,Tutor t) {
		this.dataNascimento = dataNascimento != null ? dataNascimento : this.dataNascimento;
		this.especie = especie != null ? Especie.valueOf(especie) : this.especie;
		this.nome = nome != null ? nome : this.nome;
		this.raca = raca != null ? raca : this.raca;
		this.tutor = t != null ? t : this.tutor;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Especie getEspecie() {
		return especie;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
}
