package br.com.petcare.service.form;

import java.util.Optional;

import br.com.petcare.domain.Animal;
import br.com.petcare.repository.AnimalRepository;
import br.com.petcare.web.rest.error.NotFoundException;

public class AnimalAtualizarForm{
	
	private String nome;
	
	public AnimalAtualizarForm() {
	}
	
	public Animal atualizar(Long id, AnimalRepository animalRepository) {
		Optional<Animal> a = animalRepository.findById(id);
		if(!a.isPresent())
			throw new NotFoundException("Animal com o id " + id + " não localizado");
		
		a.get().setNome( this.nome != null ? this.nome : a.get().getNome() );
		
		return a.get();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}