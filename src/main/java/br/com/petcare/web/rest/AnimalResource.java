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

import br.com.petcare.domain.Animal;
import br.com.petcare.repository.AnimalRepository;
import br.com.petcare.repository.TutorRepository;
import br.com.petcare.service.dto.AnimalDto;
import br.com.petcare.service.form.AnimalAtualizarForm;
import br.com.petcare.service.form.AnimalForm;
import br.com.petcare.web.rest.error.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/petcare/animal")
public class AnimalResource {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@ApiOperation(value="Realiza o cadastro de um animal")
	@Transactional
	@PostMapping("cadastrar")
	public ResponseEntity<AnimalDto> cadastrar(@RequestBody AnimalForm form, UriComponentsBuilder uriBuilder){
		Animal a = form.converter(tutorRepository);
		animalRepository.save(a);
		
		URI uri = uriBuilder.path("/petcare/animal/busca/id/{id}").buildAndExpand(a.getId()).toUri();
		return ResponseEntity.created( uri ).body( new AnimalDto(a) );
	}
	
	@ApiOperation(value="Retorna uma lista com todos os animais")
	@GetMapping("/listar")
	public ResponseEntity<List<AnimalDto>> listaTodos(){
		List<Animal> list = animalRepository.findAll();
		return ResponseEntity.ok(new AnimalDto().converter(list));
	}

	@ApiOperation(value="Realiza a busca de um determinado animal pelo id")
	@GetMapping("/buscar/id/{id}")
	public ResponseEntity<AnimalDto> buscarPorId(@PathVariable Long id ){
		Optional<Animal> a = animalRepository.findById(id);
		if(!a.isPresent())
			throw new NotFoundException("Animal com o id " + id + " não localizado");
		return ResponseEntity.ok(new AnimalDto(a.get()));
	}

	@ApiOperation(value="Atualiza dados cadastrais do animal pelo id informado")
	@Transactional
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<AnimalDto> atualizar(@PathVariable Long id, @RequestBody AnimalAtualizarForm form){
		Animal a = form.atualizar(id, animalRepository);
		return ResponseEntity.ok( new AnimalDto(a) );
	}

	@ApiOperation(value="Realiza a busca do animal pelo nome informado")
	@GetMapping("/buscar/nome/{nome}")
	public ResponseEntity<List<AnimalDto>> buscarPorNome(@PathVariable String nome ){
		Optional<List<Animal>> list = animalRepository.findByNome(nome.toUpperCase());
		if(!list.isPresent())
			throw new NotFoundException("Animal com o nome " + nome + " não localizado");
		return ResponseEntity.ok( new AnimalDto().converter( list.get() ));
	}

}

