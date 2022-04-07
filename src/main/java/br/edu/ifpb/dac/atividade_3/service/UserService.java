package br.edu.ifpb.dac.atividade_3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.atividade_3.model.User;
import br.edu.ifpb.dac.atividade_3.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	public User createOrUpdate(User user) {
		return userRepo.save(user);
	}
	
	public List<User> getAll() {
		return userRepo.findAll();
	}
	public User findById(Long id){
		Optional<User> user = userRepo.findById(id);

		return user.isPresent()? user.get() : null;
	}
	public void delete(User user) {
		userRepo.delete(user);
	}
}
