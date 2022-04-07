package br.edu.ifpb.dac.atividade_3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.atividade_3.model.Bracelet;
import br.edu.ifpb.dac.atividade_3.repository.BraceletRepository;

@Service
public class BraceletService {
	@Autowired
	private BraceletRepository braceletRepo;
	
	public Bracelet createOrUpdate(Bracelet bracelet) {
		return braceletRepo.save(bracelet);
	}
	
	public List<Bracelet> getAll() {
		return braceletRepo.findAll();
	}
	public Bracelet findById(Long id){
		Optional<Bracelet> bracelet = braceletRepo.findById(id);

		return bracelet.isPresent()? bracelet.get() : null;
	}
	public void delete(Bracelet Bracelet) {
		braceletRepo.delete(Bracelet);
	}
}
