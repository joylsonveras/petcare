package br.com.petcare.service.form;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.petcare.domain.Animal;
import br.com.petcare.domain.Tutor;
import br.com.petcare.repository.TutorRepository;
import br.com.petcare.web.rest.error.NotFoundException;
import io.swagger.annotations.ApiModelProperty;

public class AnimalForm{
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@ApiModelProperty(dataType="br.com.petcare.domain.enumeration.Especie", example = "enum")
	private String especie;
	private String nome;
	private String raca;
	private Long tutorId;
	
	public AnimalForm() {
	}
	
	public Animal converter(TutorRepository tutorRepository) {
		Optional<Tutor> t = tutorRepository.findById(tutorId);
		if(!t.isPresent())
			throw new NotFoundException("Tutor com o id "+ this.tutorId + " não foi localizado" );
		return new Animal(this.dataNascimento, this.especie , this.nome, this.raca, t.get());
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}
	
}