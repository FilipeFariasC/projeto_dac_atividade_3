package br.edu.ifpb.dac.atividade3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.atividade3.dto.UserDto;
import br.edu.ifpb.dac.atividade3.dto.UserPostDto;
import br.edu.ifpb.dac.atividade3.model.User;
import br.edu.ifpb.dac.atividade3.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper userMapper;
	
	public UserDto createOrUpdate(UserPostDto userDto) {
		User created = userRepo.save(userMapper.map(userDto, User.class));
		
		return userMapper.map(created, UserDto.class);
	}
	
	public List<UserDto> getAll() {
		List<User> users = userRepo.findAll();

		return users
				.stream()
				.map((user) -> userMapper.map(user, UserDto.class))
				.toList();
	}
	public UserDto findById(Long id){
		Optional<User> user = userRepo.findById(id);

		return user.isPresent()? userMapper.map(user.get(), UserDto.class): null;
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
