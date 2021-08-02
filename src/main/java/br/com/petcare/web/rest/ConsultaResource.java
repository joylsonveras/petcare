package br.com.petcare.web.rest;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petcare.domain.Animal;
import br.com.petcare.domain.Consulta;
import br.com.petcare.domain.Veterinario;
import br.com.petcare.domain.enumeration.Status;
import br.com.petcare.repository.AnimalRepository;
import br.com.petcare.repository.ConsultaRepository;
import br.com.petcare.repository.VeterinarioRepository;
import br.com.petcare.service.dto.ConsultaDto;
import br.com.petcare.service.form.ConsultaAtualizarForm;
import br.com.petcare.service.form.ConsultaForm;
import br.com.petcare.web.rest.error.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/petcare/consulta")
public class ConsultaResource {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@ApiOperation(value="Realiza o agendamento de uma consulta")
	@Transactional
	@PostMapping("cadastrar")
	public ResponseEntity<ConsultaDto> cadastrar(@RequestBody ConsultaForm form, UriComponentsBuilder uriBuilder){
		
		Optional<Animal> a = animalRepository.findById( form.getAnimalId() );
		if(!a.isPresent())
			throw new NotFoundException("Animal com o id " + form.getAnimalId() + " não localizado");
		
		Optional<Veterinario> v = veterinarioRepository.findById( form.getVeterinarioId() );
		if(!v.isPresent())
			throw new NotFoundException("Veterinario com o id " + form.getVeterinarioId() + " não localizado");
		
		if(form.getDataConsulta().isBefore(LocalDate.now()))
			throw new RuntimeException("A data da consulta não pode ser menor que a data de hoje");
		
		Consulta c = new Consulta(form.getDataConsulta(), Status.AGENDADA, a.get(), v.get());
		consultaRepository.save(c);
		
		URI uri = uriBuilder.path("/petcare/consulta/busca/id/{id}").buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created( uri ).body( new ConsultaDto(c) );
	}
	
	@ApiOperation(value="Retorna uma lista com todas as consultas")
	@GetMapping("/listar")
	public ResponseEntity<List<ConsultaDto>> listaTodos(){
		List<Consulta> list = consultaRepository.findAll();
		return ResponseEntity.ok(new ConsultaDto().converter(list));
	}

	@ApiOperation(value="Realiza a busca de um consulta por determinado id")
	@GetMapping("/buscar/id/{id}")
	public ResponseEntity<ConsultaDto> buscarPorId(@PathVariable Long id ){
		Optional<Consulta> c = consultaRepository.findById(id);
		if(!c.isPresent())
			throw new NotFoundException("Consulta com o id " + id + " não localizada");
		return ResponseEntity.ok(new ConsultaDto(c.get()));
	}

	@ApiOperation(value="Cancela a consulta pelo id")
	@Transactional
	@PutMapping("/cancelar/{id}")
	public ResponseEntity<ConsultaDto> cancelar(@PathVariable Long id){
		Consulta c = new ConsultaAtualizarForm().cancelar(id, consultaRepository);
		return ResponseEntity.ok( new ConsultaDto( c ) );
	}

	@ApiOperation(value="Altera a data da consulta pelo id")
	@Transactional
	@PutMapping("/alterar/{id}")
	public ResponseEntity<ConsultaDto> cancelar(@PathVariable Long id, @RequestBody ConsultaAtualizarForm form){
		Consulta c = form.mudarData(id, consultaRepository);
		return ResponseEntity.ok( new ConsultaDto( c ) );
	}
	
	@ApiOperation(value="Realiza a exclusão de uma consulta cancelada")
	@Transactional
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		Optional<Consulta> c = consultaRepository.findById(id);
		if(!c.isPresent())
			throw new NotFoundException("Consulta com o id " + id + " não localizada");
		
		if( !Status.CANCELADA.equals(c.get().getStatus() ) ) 
			throw new NotFoundException("Não e possivel cancelar uma consulta que não foi cancelada");
		
		consultaRepository.delete(c.get());
		
		return ResponseEntity.ok().build();
	}

}

