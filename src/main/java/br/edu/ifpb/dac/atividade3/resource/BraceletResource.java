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

import br.edu.ifpb.dac.atividade3.dto.BraceletDto;
import br.edu.ifpb.dac.atividade3.dto.BraceletPostDto;
import br.edu.ifpb.dac.atividade3.service.BraceletService;

@RestController
@RequestMapping("/bracelets")
public class BraceletResource {
	
	@Autowired
	private BraceletService braceletService;

	/*
	 *  se houver pulseiras, retorna as pulseiras.
	 *  se não houver, retorna uma lista vazia.
	 */
	@GetMapping
	public List<BraceletDto> get(){
		return braceletService.getAll();
	}
	/*
	 * Se houver um usuário com esse id, retorna 200 OK e o usuário no corpo da resposta
	 * Se não houver, retorna um 404 Not Found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id){
		BraceletDto bracelet = braceletService.findById(id);
		
		return bracelet == null ? ResponseEntity.ok(bracelet) : ResponseEntity.notFound().build();
	}
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> saveUser(
			@Valid @RequestBody BraceletPostDto braceletPostDto,
			HttpServletResponse response
			){
		BraceletDto created = braceletService.createOrUpdate(braceletPostDto);
		
		// cria o uri locator da nova pulseira
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(created.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(created);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
//			@Valid
			@RequestBody BraceletPostDto braceletDto, 
			@PathVariable("id") Long id){
		if(braceletService.findById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		BraceletDto updated = braceletService.createOrUpdate(braceletDto);
		return ResponseEntity.ok(updated);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return braceletService.deleteById(id) ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
	}
}
