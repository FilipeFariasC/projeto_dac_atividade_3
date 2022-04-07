package br.edu.ifpb.dac.atividade_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.atividade_3.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
