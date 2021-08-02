package br.com.petcare.service.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.petcare.domain.Animal;
import io.swagger.annotations.ApiModelProperty;

public class AnimalDto{
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@ApiModelProperty(dataType="br.com.petcare.domain.enumeration.Especie", example = "enum")
	private String especie;
	private String nome;
	private String raca;
	private TutorDto tutor;
	
	public AnimalDto() {
	}
	
	public AnimalDto(Animal animal) {
		this.dataNascimento = animal.getDataNascimento();
		this.especie = animal.getEspecie().name();
		this.nome = animal.getNome();
		this.raca = animal.getRaca();
		this.tutor = new TutorDto( animal.getTutor() );
	}
	
	public List<AnimalDto> converter(List<Animal> list){
		return list.stream().map(AnimalDto::new).collect(Collectors.toList());
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public String getEspecie() {
		return especie;
	}
	public String getNome() {
		return nome;
	}
	public String getRaca() {
		return raca;
	}
	public TutorDto getTutor() {
		return tutor;
	}
	
}