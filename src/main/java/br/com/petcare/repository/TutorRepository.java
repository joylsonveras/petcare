package br.com.petcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petcare.domain.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
	
	@Query("SELECT t FROM Tutor t WHERE UPPER(t.nome) LIKE %:nome%  ")
	Optional<List<Tutor>> findByNome(@Param("nome") String nome);

}
