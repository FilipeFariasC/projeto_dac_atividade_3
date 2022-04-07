package br.edu.ifpb.dac.atividade_3.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Size(min=3, max=50)
	private String name;
	
	@NotNull
	@Size(max=30)
	private String password;
	
	@NotNull
	@NotEmpty
	private String email;
	
	@OneToMany(mappedBy="user")
	private Set<Bracelet> bracelets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public Set<Bracelet> getBracelets() {
		return bracelets;
	}
	public void setBracelets(Set<Bracelet> bracelets) {
		this.bracelets = bracelets;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
