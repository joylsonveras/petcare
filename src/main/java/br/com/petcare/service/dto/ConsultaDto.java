package br.com.petcare.service.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.petcare.domain.Consulta;

public class ConsultaDto{
	
	private Long id;
	private LocalDate dataConsulta;
	private String status;
	private AnimalDto animal;
	private VeterinarioDto veterinario;
	
	public ConsultaDto() {
	}
	
	public ConsultaDto(Consulta consulta) {
		this.id = consulta.getId();
		this.dataConsulta = consulta.getDataConsulta();
		this.status = consulta.getStatus().name();
		this.animal = new AnimalDto( consulta.getAnimal() );
		this.veterinario = new VeterinarioDto( consulta.getVeterinario() );
	}
	
	public List<ConsultaDto> converter(List<Consulta> list){
		return list.stream().map(ConsultaDto::new).collect(Collectors.toList());
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AnimalDto getAnimal() {
		return animal;
	}
	public void setAnimal(AnimalDto animal) {
		this.animal = animal;
	}
	public VeterinarioDto getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(VeterinarioDto veterinario) {
		this.veterinario = veterinario;
	}

}