package com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.model.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	 List<Cliente> findByNome(@Param("nome")String nome);
}
