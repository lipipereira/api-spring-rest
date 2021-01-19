package com.desenvolvedor.osworks.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvedor.osworks.api.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome); // Busca por um nome certo
	List<Cliente> findByNomeContaining(String nome); // Busca por letras que contem no nome	
	Cliente findByEmail(String email); // Consulta pelo emial retorna somente Email	
}
