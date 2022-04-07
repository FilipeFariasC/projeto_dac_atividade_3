package br.edu.ifpb.dac.atividade3.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BraceletPostDto {
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String name;
	
	public String getName() {
		return name;
	}
	public void setNome(String name) {
		this.name = name;
	}
	
}
