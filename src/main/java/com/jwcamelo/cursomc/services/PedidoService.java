package com.jwcamelo.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jwcamelo.cursomc.domain.Pedido;
import com.jwcamelo.cursomc.repositories.PedidoRepository;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public Optional<Pedido> find(Integer id){
    	Optional<Pedido> pedido = repo.findById(id);
    	if(!pedido.isPresent())
    		throw new ObjectNotFoundException("Objeto não encontrado: Id: "+id+" , Tipo: "+Pedido.class);
    	return pedido;
    }

}
