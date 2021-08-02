package br.com.petcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petcare.domain.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
	
	@Query("SELECT t FROM Veterinario t WHERE UPPER(t.nome) LIKE %:nome%  ")
	Optional<List<Veterinario>> findByNome(@Param("nome") String nome);

}