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
	
	public Bracelet create(BraceletPostDto braceletPostDto) {
		Bracelet created = braceletRepo.save(braceletMapper.map(braceletPostDto, Bracelet.class));
		
		return created;
	}
	
	public List<Bracelet> getAll() {
		List<Bracelet> bracelets = braceletRepo.findAll();
		
		return bracelets;
	}
	public Bracelet findById(Long id){
		Optional<Bracelet> bracelet = braceletRepo.findById(id);

		return bracelet.isPresent() ? bracelet.get(): null;
	}
	public Bracelet update(Long id, BraceletPostDto update) {
		if(braceletRepo.findById(id).isEmpty())
			return null;
		
		Bracelet bracelet = braceletMapper.map(update, Bracelet.class);
		bracelet.setId(id);
		
		return braceletRepo.save(bracelet);
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
