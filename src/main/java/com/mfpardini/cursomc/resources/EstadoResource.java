package com.mfpardini.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfpardini.cursomc.domain.Cidade;
import com.mfpardini.cursomc.domain.Estado;
import com.mfpardini.cursomc.dto.CidadeDTO;
import com.mfpardini.cursomc.dto.EstadoDTO;
import com.mfpardini.cursomc.services.CidadeService;
import com.mfpardini.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	EstadoService service;
	
	@Autowired
	CidadeService cidadeService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> find(@PathVariable Integer id) {
		Estado obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{estado_id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidadeByEstadoId(@PathVariable Integer estado_id) {
		List<Cidade> list = cidadeService.findAllByEstadoId(estado_id);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
