package br.edu.ifpb.dac.atividade3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.atividade3.dto.UserPostDto;
import br.edu.ifpb.dac.atividade3.model.User;
import br.edu.ifpb.dac.atividade3.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper userMapper;
	
	public User create(UserPostDto userDto) {
		User created = userRepo.save(userMapper.map(userDto, User.class));
		
		return created;
	}
	
	public List<User> getAll() {
		List<User> users = userRepo.findAll();

		return users;
	}
	public User findById(Long id){
		Optional<User> user = userRepo.findById(id);

		return user.isPresent()? user.get(): null;
	}
	
	public User update(Long id, UserPostDto userPostDto) {	
		if(userRepo.findById(id).isEmpty())
			return null;
		
		User update = userMapper.map(userPostDto, User.class);
		update.setId(id);
		
		return userRepo.save(update);
	}
	
	public void delete(User user) {
		userRepo.delete(user);
	}
	public boolean deleteById(Long id) {
		if(userRepo.findById(id).isEmpty())
			return false;
		userRepo.deleteById(id);
		return true;
	}
}
