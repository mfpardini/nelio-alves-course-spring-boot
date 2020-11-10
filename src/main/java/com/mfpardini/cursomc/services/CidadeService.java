package com.mfpardini.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpardini.cursomc.domain.Cidade;
import com.mfpardini.cursomc.repositories.CidadeRepository;
import com.mfpardini.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo " + Cidade.class.getName()
				));
	}
	
	public List<Cidade> findAllByEstadoId(Integer estado_id) {
		return repo.findByEstado(estado_id);
	}
	
}
