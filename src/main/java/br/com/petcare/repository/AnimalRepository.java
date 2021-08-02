package br.com.petcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petcare.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	@Query("SELECT t FROM Animal t WHERE UPPER(t.nome) LIKE %:nome%  ")
	Optional<List<Animal>> findByNome(@Param("nome") String nome);
	
}
