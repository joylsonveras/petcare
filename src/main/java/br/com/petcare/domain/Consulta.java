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

import br.com.petcare.domain.enumeration.Status;

@Entity
public class Consulta {
	 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id;
	private LocalDate dataConsulta;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	@JoinColumn(name="animal")
	private Animal animal;
	@ManyToOne
	@JoinColumn(name="veterinario")
	private Veterinario veterinario;
	
	public Consulta() {
	}
	
	public Consulta(LocalDate dataConsulta, Status status, Animal animal, Veterinario veterinario) {
		this.dataConsulta = dataConsulta != null ? dataConsulta : this.dataConsulta;
		this.status = status != null ? status : this.status;
		this.animal = animal != null ? animal : this.animal;
		this.veterinario = veterinario != null ? veterinario : this.veterinario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

}
