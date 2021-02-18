package com.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teste.model.Cliente;
import com.teste.repository.ClienteRepository;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Buscar clientes
	@GetMapping("/clientes")
	public List<Cliente> buscar() {
		return clienteRepository.findAll();
	}
	
	//Buscar cliente pelo id
	@GetMapping("/cliente/{id}")
	public Optional<Cliente> buscarPorId(@PathVariable("id") long id) {
		return clienteRepository.findById(id);
	}
	
	//Salvar cliente
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	//Buscar cliente pelo nome
	@GetMapping("/clientes/{nome}")
	public List<Cliente> buscarPorNome(@PathVariable("nome") String nome) {
		return clienteRepository.findByNome(nome);
	}
	
	//Alterar dados do cliente
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> alterarCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		Optional<Cliente> dadosCliente = clienteRepository.findById(id);
		
		if(dadosCliente.isPresent()) {
			Cliente clienteSave = dadosCliente.get();
			clienteSave.setNome(cliente.getNome());
			clienteSave.setSexo(cliente.getSexo());
			clienteSave.setDataNascimento(cliente.getDataNascimento());
			clienteSave.setIdade(cliente.getIdade());
			clienteSave.setCidade(cliente.getCidade());

			return new ResponseEntity<>(clienteRepository.save(clienteSave), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND)		;
		}		
	}
	
	//Deletar cliente
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<HttpStatus> deletarCliente(@PathVariable("id") long id){

		try {
			clienteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}