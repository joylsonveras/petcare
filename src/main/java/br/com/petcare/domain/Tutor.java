package br.com.petcare.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tutor extends DadosPessoais{
	 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id;
	
	public Tutor() {
	}
	public Tutor(String nome, String telefone, String email) {
		this.nome = nome != null ? nome : this.nome;
		this.telefone = telefone != null ? telefone : this.telefone;
		this.email = email != null ? email : this.email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
