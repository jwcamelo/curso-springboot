package com.jwcamelo.cursomc.cursomc.services;

import com.jwcamelo.cursomc.cursomc.domain.Categoria;
import com.jwcamelo.cursomc.cursomc.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public Optional<Categoria> find(Integer id){
        return repo.findById(id);
    }

}
