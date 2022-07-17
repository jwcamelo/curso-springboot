package com.jwcamelo.cursomc.services;

import com.jwcamelo.cursomc.domain.Cliente;
import com.jwcamelo.cursomc.repositories.ClienteRepository;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Optional<Cliente> find(Integer id){
    	Optional<Cliente> Cliente = repo.findById(id);
    	if(!Cliente.isPresent())
    		throw new ObjectNotFoundException("Objeto não encontrado: Id: "+id+" , Tipo: "+Cliente.class);
    	return Cliente;
    }

}
