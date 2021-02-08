package com.luanbraga.projetofullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanbraga.projetofullstack.domain.Cliente;
import com.luanbraga.projetofullstack.repositories.ClienteRepository;
import com.luanbraga.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Parece que houve um problema, ligue para a polícia e passe essas informações: Id= " + 
				id + ", Tipo= " + Cliente.class.getName())); 
	}
}
