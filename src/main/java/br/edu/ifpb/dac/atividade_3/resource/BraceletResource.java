package br.edu.ifpb.dac.atividade_3.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.atividade_3.service.BraceletService;

@RestController
@RequestMapping("/")
public class BraceletResource {
	
	@Autowired
	private BraceletService braceletService;
	
}
