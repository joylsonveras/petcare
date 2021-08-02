package br.com.petcare.service.form;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.petcare.domain.Consulta;
import br.com.petcare.domain.enumeration.Status;
import br.com.petcare.repository.ConsultaRepository;
import br.com.petcare.web.rest.error.NotFoundException;

public class ConsultaAtualizarForm{
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataConsulta;
	
	public ConsultaAtualizarForm() {
	}
	
	public Consulta mudarData(Long id, ConsultaRepository consultaRepository) {
		Optional<Consulta> c = consultaRepository.findById(id);
		if(!c.isPresent())
			throw new NotFoundException("Consulta com o id " + id + " não localizada");
		
		if(this.dataConsulta.isBefore(LocalDate.now()))
			throw new RuntimeException("Nova data da consulta não pode ser menor que a data de hoje");
		
		c.get().setStatus( Status.CANCELADA );
		return c.get();
	}
	
	public Consulta cancelar(Long id, ConsultaRepository consultaRepository) {
		Optional<Consulta> c = consultaRepository.findById(id);
		if(!c.isPresent())
			throw new NotFoundException("Consulta com o id " + id + " não localizada");
		c.get().setStatus( Status.CANCELADA );
		return c.get();
	}

}
	