package br.edu.ifpb.dac.atividade3.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifpb.dac.atividade3.dto.UserDto;
import br.edu.ifpb.dac.atividade3.dto.UserPostDto;
import br.edu.ifpb.dac.atividade3.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	/*
	 *  se houver usuários, retorna os usuários.
	 *  se não houver, retorna uma lista vazia.
	*/
	@GetMapping
	public List<UserDto> get(){
		return userService.getAll();
	}
	/*
	 * Se houver um usuário com esse id, retorna 200 OK e o usuário no corpo da resposta
	 * Se não houver, retorna um 404 Not Found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id){
		UserDto user = userService.findById(id);
		
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}
	/*
	 * Recebe um usuário válido e uma response
	 * ao criar o usuário, é retornado um status 200 CREATED, com a uri de localização desse novo usuario e as informacoes do usuario no seu corpo.
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> saveUser(
			@Valid 
			@RequestBody UserPostDto userPostDto,
			HttpServletResponse response){
		UserDto created = userService.createOrUpdate(userPostDto);
		
		// cria o uri locator do novo usuario
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(created.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(created);
	}
	
	/*
	 * Atualiza o usuário do id 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
//			@Valid 
			@RequestBody 
			UserPostDto userPostDto, @PathVariable("id") Long id){
		if(userService.findById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		
		UserDto updated = userService.createOrUpdate(userPostDto);
		return ResponseEntity.ok(updated);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return userService.deleteById(id) ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
	}
	
	
}
