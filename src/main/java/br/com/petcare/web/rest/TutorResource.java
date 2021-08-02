package br.com.petcare.web.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petcare.domain.Tutor;
import br.com.petcare.repository.TutorRepository;
import br.com.petcare.service.dto.TutorDto;
import br.com.petcare.service.form.TutorForm;
import br.com.petcare.web.rest.error.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/petcare/tutor")
public class TutorResource {
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@ApiOperation(value="Realiza o cadastro de um tutor")
	@Transactional
	@PostMapping("cadastrar")
	public ResponseEntity<TutorDto> cadastrar(@RequestBody TutorForm form, UriComponentsBuilder uriBuilder){
		Tutor t = form.converter();
		tutorRepository.save(t);
		
		URI uri = uriBuilder.path("/petcare/tutor/busca/id/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created( uri ).body( new TutorDto(t) );
	}
	
	@ApiOperation(value="Retorna uma lista com todos os tutores")
	@GetMapping("/listar")
	public ResponseEntity<List<TutorDto>> listarTodos(){
		List<Tutor> list = tutorRepository.findAll();
		return ResponseEntity.ok(new TutorDto().converter(list));
	}
	
	@ApiOperation(value="Realiza a busca de um determinado tutor pelo id")
	@GetMapping("/buscar/id/{id}")
	public ResponseEntity<TutorDto> buscarPorId(@PathVariable Long id ){
		Optional<Tutor> t = tutorRepository.findById(id);
		if(!t.isPresent())
			throw new NotFoundException("Tutor com o id " + id + " não localizado");
		return ResponseEntity.ok(new TutorDto(t.get()));
	}
	
	@ApiOperation(value="Atualiza dados cadastrais do tutor pelo id informado")
	@Transactional
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<TutorDto> atualizar(@PathVariable Long id, @RequestBody TutorForm form){
		Tutor t = form.atualizar(id, tutorRepository);
		return ResponseEntity.ok( new TutorDto(t) );
	}
	
	@ApiOperation(value="Realiza a busca do tutor pelo nome informado")
	@GetMapping("/buscar/nome/{nome}")
	public ResponseEntity<List<TutorDto>> buscarPorNome(@PathVariable String nome ){
		Optional<List<Tutor>> list = tutorRepository.findByNome(nome.toUpperCase());
		if(!list.isPresent())
			throw new NotFoundException("Tutor com o nome " + nome + " não localizado");
		return ResponseEntity.ok( new TutorDto().converter( list.get() ));
	}
}

