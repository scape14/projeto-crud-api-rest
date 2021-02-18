package com.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	@Query("SELECT c FROM Cidade c WHERE c.nomeCidade LIKE %:nomeCidade%")
	 Optional<Cidade> findByNome(@Param("nomeCidade")String nomeCidade);
	
	@Query("SELECT c FROM Cidade c WHERE c.estado LIKE %:estado%")
	 Optional<Cidade> findByEstado(@Param("estado")String estado);
}
