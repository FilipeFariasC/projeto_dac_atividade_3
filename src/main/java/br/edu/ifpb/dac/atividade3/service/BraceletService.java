package br.edu.ifpb.dac.atividade3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.atividade3.dto.BraceletDto;
import br.edu.ifpb.dac.atividade3.dto.BraceletPostDto;
import br.edu.ifpb.dac.atividade3.model.Bracelet;
import br.edu.ifpb.dac.atividade3.repository.BraceletRepository;

@Service
public class BraceletService {
	@Autowired
	private BraceletRepository braceletRepo;
	
	@Autowired
	private ModelMapper braceletMapper;
	
	public BraceletDto create(BraceletPostDto braceletPostDto) {
		Bracelet created = braceletRepo.save(braceletMapper.map(braceletPostDto, Bracelet.class));
		
		return braceletMapper.map(created, BraceletDto.class);
	}
	
	public List<BraceletDto> getAll() {
		List<Bracelet> bracelets = braceletRepo.findAll();
		
		return bracelets
				.stream()
				.map((bracelet) -> braceletMapper.map(bracelet, BraceletDto.class))
				.toList();
	}
	public BraceletDto findById(Long id){
		Optional<Bracelet> bracelet = braceletRepo.findById(id);

		return bracelet.isPresent() ? 
				braceletMapper.map(bracelet.get(), BraceletDto.class) : null;
	}
	public BraceletDto update(Long id, BraceletPostDto update) {
		if(braceletRepo.findById(id).isEmpty())
			return null;
		
		Bracelet bracelet = braceletMapper.map(update, Bracelet.class);
		bracelet.setId(id);
		
		return braceletMapper.map(bracelet, BraceletDto.class);
	}
	
	public void delete(Bracelet bracelet) {
		braceletRepo.delete(bracelet);
	}
	public boolean deleteById(Long id) {
		if(braceletRepo.findById(id).isEmpty())
			return false;
		
		braceletRepo.deleteById(id);
		return true;
	}
}
