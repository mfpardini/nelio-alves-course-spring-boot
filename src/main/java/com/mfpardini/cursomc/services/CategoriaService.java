package com.mfpardini.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpardini.cursomc.domain.Categoria;
import com.mfpardini.cursomc.repositories.CategoriaRepository;
import com.mfpardini.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo " + Categoria.class.getName()
				));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		this.find(obj.getId());
		return repo.save(obj);
	}
}
