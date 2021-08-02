package br.com.petcare.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.petcare.domain.DadosPessoais;
import br.com.petcare.domain.Tutor;

public class TutorDto extends DadosPessoais {
	
	private Long id;
	
	public TutorDto() {
	}
	
	public TutorDto(Tutor tutor) {
		this.email = tutor.getEmail();
		this.nome = tutor.getNome();
		this.telefone = tutor.getTelefone();
		this.id = tutor.getId();
	}
	
	public List<TutorDto> converter(List<Tutor> list){
		return list.stream().map(TutorDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
