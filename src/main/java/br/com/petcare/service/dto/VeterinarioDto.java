package br.com.petcare.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.petcare.domain.DadosPessoais;
import br.com.petcare.domain.Veterinario;

public class VeterinarioDto extends DadosPessoais {
	
	private Long id;
	
	public VeterinarioDto() {
	}
	
	public VeterinarioDto(Veterinario veterinario) {
		this.email = veterinario.getEmail();
		this.nome = veterinario.getNome();
		this.telefone = veterinario.getTelefone();
		this.id = veterinario.getId();
	}
	
	public List<VeterinarioDto> converter(List<Veterinario> list){
		return list.stream().map(VeterinarioDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
