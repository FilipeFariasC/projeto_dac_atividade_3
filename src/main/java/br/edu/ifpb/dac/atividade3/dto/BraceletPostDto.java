package br.edu.ifpb.dac.atividade3.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BraceletPostDto {
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
