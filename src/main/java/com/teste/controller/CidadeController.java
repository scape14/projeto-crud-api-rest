package com.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.model.Cidade;
import com.teste.repository.CidadeRepository;

@RestController
@RequestMapping("/api")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	//Buscar todas as cidades
	@GetMapping("/cidades")
	public List<Cidade> buscar() {
		return cidadeRepository.findAll();
	}
	
	//Salvar cidade
	@PostMapping("/cidades")
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	//Buscar cidade pelo nome
	@GetMapping("/cidades/{nomeCidade}")
	public Optional<Cidade> buscarPorNome(@PathVariable("nomeCidade") String nomeCidade) {
		return cidadeRepository.findByNome(nomeCidade);
	}
	
	//Buscar cidade pelo estado
	@GetMapping("/cidades/estado/{estado}")
	public Optional<Cidade> buscarPorEstado(@PathVariable("estado") String estado) {
		return cidadeRepository.findByEstado(estado);
	}
}
