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

import br.com.petcare.domain.Veterinario;
import br.com.petcare.repository.VeterinarioRepository;
import br.com.petcare.service.dto.VeterinarioDto;
import br.com.petcare.service.form.VeterinarioForm;
import br.com.petcare.web.rest.error.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/petcare/veterinario")
public class VeterinarioResource {
	
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@ApiOperation(value="Realiza o cadastro de um veterinario")
	@Transactional
	@PostMapping("cadastrar")
	public ResponseEntity<VeterinarioDto> cadastrar(@RequestBody VeterinarioForm form, UriComponentsBuilder uriBuilder){
		Veterinario v = form.converter();
		veterinarioRepository.save(v);
		
		URI uri = uriBuilder.path("/petcare/veterinario/busca/id/{id}").buildAndExpand(v.getId()).toUri();
		return ResponseEntity.created( uri ).body( new VeterinarioDto(v) );
	}
	
	@ApiOperation(value="Retorna uma lista com todos os veterinarios")
	@GetMapping("/listar")
	public ResponseEntity<List<VeterinarioDto>> listaTodos(){
		List<Veterinario> list = veterinarioRepository.findAll();
		return ResponseEntity.ok(new VeterinarioDto().converter(list));
	}
	
	@ApiOperation(value="Realiza a busca de um determinado veterinario pelo id")
	@GetMapping("/buscar/id/{id}")
	public ResponseEntity<VeterinarioDto> buscarPorId(@PathVariable Long id ){
		Optional<Veterinario> v = veterinarioRepository.findById(id);
		if(!v.isPresent())
			throw new NotFoundException("Veterinario com o id " + id + " não localizado");
		return ResponseEntity.ok(new VeterinarioDto(v.get()));
	}
	
	@ApiOperation(value="Atualiza dados cadastrais do veterinario pelo id informado")
	@Transactional
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<VeterinarioDto> atualizar(@PathVariable Long id, @RequestBody VeterinarioForm form){
		Veterinario v = form.atualizar(id, veterinarioRepository);
		return ResponseEntity.ok( new VeterinarioDto(v) );
	}
	
	@ApiOperation(value="Realiza a busca do veterinario pelo nome informado")
	@GetMapping("/buscar/nome/{nome}")
	public ResponseEntity<List<VeterinarioDto>> buscarPorNome(@PathVariable String nome ){
		Optional<List<Veterinario>> list = veterinarioRepository.findByNome(nome.toUpperCase());
		if(!list.isPresent())
			throw new NotFoundException("Tutor com o nome " + nome + " não localizado");
		return ResponseEntity.ok( new VeterinarioDto().converter( list.get() ));
	}
	
}

