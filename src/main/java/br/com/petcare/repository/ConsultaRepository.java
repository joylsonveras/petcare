package br.com.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petcare.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
