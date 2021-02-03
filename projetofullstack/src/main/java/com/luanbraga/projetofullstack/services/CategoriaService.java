package com.luanbraga.projetofullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanbraga.projetofullstack.domain.Categoria;
import com.luanbraga.projetofullstack.repositories.CategoriaRepository;
import com.luanbraga.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Parece que houve um problema, ligue para a polícia e passe essas informações: Id= " + 
				id + ", Tipo= " + Categoria.class.getName())); 
	}
}
