package com.luanbraga.projetofullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanbraga.projetofullstack.domain.Pedido;
import com.luanbraga.projetofullstack.repositories.PedidoRepository;
import com.luanbraga.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Parece que houve um problema, ligue para a polícia e passe essas informações: Id= " + 
				id + ", Tipo= " + Pedido.class.getName())); 
	}
}