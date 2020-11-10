package com.mfpardini.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpardini.cursomc.domain.Estado;
import com.mfpardini.cursomc.repositories.EstadoRepository;
import com.mfpardini.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo " + Estado.class.getName()
				));
	}
	
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
	
}
