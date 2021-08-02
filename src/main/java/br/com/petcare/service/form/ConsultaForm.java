package br.com.petcare.service.form;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultaForm{
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataConsulta;
	private Long animalId;
	private Long veterinarioId;
	
	public ConsultaForm() {
	}
	
	public LocalDate getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public Long getAnimalId() {
		return animalId;
	}
	public void setAnimalId(Long animalId) {
		this.animalId = animalId;
	}
	public Long getVeterinarioId() {
		return veterinarioId;
	}
	public void setVeterinarioId(Long veterinarioId) {
		this.veterinarioId = veterinarioId;
	}

}
	