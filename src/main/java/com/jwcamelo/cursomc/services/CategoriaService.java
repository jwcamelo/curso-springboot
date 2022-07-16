package com.jwcamelo.cursomc.services;

import com.jwcamelo.cursomc.domain.Categoria;
import com.jwcamelo.cursomc.repositories.CategoriaRepository;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public Optional<Categoria> find(Integer id){
    	Optional<Categoria> categoria = repo.findById(id);
    	if(!categoria.isPresent())
    		throw new ObjectNotFoundException("Objeto não encontrado: Id: "+id+" , Tipo: "+Categoria.class);
    	return categoria;
    }

}
