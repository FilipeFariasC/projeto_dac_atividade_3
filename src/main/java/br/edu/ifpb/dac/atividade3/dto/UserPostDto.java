package br.edu.ifpb.dac.atividade3.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPostDto {
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min=3, max=50)
	private String name;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String email;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max=30)
	private String password;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}