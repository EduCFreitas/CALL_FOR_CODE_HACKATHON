package com.callForCode.vaccination.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callForCode.vaccination.model.Usuario;
import com.callForCode.vaccination.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> GetByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Usuario>> GetByEmail(@PathVariable String email){
		return ResponseEntity.ok(repository.findAllByEmailContainingIgnoreCase(email));
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Usuario>> GetByCpf(@PathVariable String cpf){
		return ResponseEntity.ok(repository.findAllByCpfContainingIgnoreCase(cpf));
	}
	
	@GetMapping("/rg/{rg}")
	public ResponseEntity<List<Usuario>> GetByRg(@PathVariable String rg){
		return ResponseEntity.ok(repository.findAllByRgContainingIgnoreCase(rg));
	}
	
	// Arrumar para login e cadastro
	@PostMapping
	public ResponseEntity<Usuario> postusuario(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	@PostMapping("/logar")
	public ResponseEntity<Usuario> Postlogar(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
