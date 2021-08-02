package br.com.petcare.service.form;

import java.util.Optional;

import br.com.petcare.domain.DadosPessoais;
import br.com.petcare.domain.Tutor;
import br.com.petcare.repository.TutorRepository;
import br.com.petcare.web.rest.error.NotFoundException;

public class TutorForm extends DadosPessoais {
	
	public Tutor converter() {
		return new Tutor(this.nome, this.telefone, this.email);
	}
	
	public Tutor atualizar(Long id, TutorRepository tutorRepository) {
		Optional<Tutor> t = tutorRepository.findById(id);
		if(!t.isPresent())
			throw new NotFoundException("Tutor com o id " + id + " não localizado");
		
		t.get().setNome( this.nome != null ? this.nome : t.get().getNome() );
		t.get().setEmail( this.email != null ? this.email : t.get().getEmail() );
		t.get().setTelefone( this.telefone != null ? this.email : t.get().getTelefone() );
		
		return t.get();
	}
	
}