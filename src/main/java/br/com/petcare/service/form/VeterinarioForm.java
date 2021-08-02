package br.com.petcare.service.form;

import java.util.Optional;

import br.com.petcare.domain.DadosPessoais;
import br.com.petcare.domain.Veterinario;
import br.com.petcare.repository.VeterinarioRepository;
import br.com.petcare.web.rest.error.NotFoundException;

public class VeterinarioForm extends DadosPessoais {
	
	public Veterinario converter() {
		return new Veterinario(this.nome, this.telefone, this.email);
	}
	
	public Veterinario atualizar(Long id, VeterinarioRepository veterinarioRepository) {
		Optional<Veterinario> v = veterinarioRepository.findById(id);
		if(!v.isPresent())
			throw new NotFoundException("Veterinario com o id " + id + " não localizado");
		
		v.get().setNome( this.nome != null ? this.nome : v.get().getNome() );
		v.get().setEmail( this.email != null ? this.email : v.get().getEmail() );
		v.get().setTelefone( this.telefone != null ? this.email : v.get().getTelefone() );
		
		return v.get();
	}
	
}