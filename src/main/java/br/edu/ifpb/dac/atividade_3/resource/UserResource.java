package br.edu.ifpb.dac.atividade_3.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifpb.dac.atividade_3.model.User;
import br.edu.ifpb.dac.atividade_3.service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	/*
	 *  se houver usuários, retorna os usuários.
	 *  se não houver, retorna uma lista vazia.
	 */
	@GetMapping
	public List<User> get(){
		return userService.getAll();
	}
	/*
	 * Se houver um usuário com esse id, retorna 200 OK e o usuário no corpo da resposta
	 * Se não houver, retorna um 404 Not Found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id){
		User user = userService.findById(id);
		
		return user == null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> saveUser(
			@Valid @RequestBody User user,
			HttpServletResponse response
			){
		User created = userService.createOrUpdate(user);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(created.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Long id){
		if(userService.findById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		
		User updated = userService.createOrUpdate(user);
		return ResponseEntity.ok(updated);
	}
	
}
