package com.jwcamelo.cursomc.resources;

import com.jwcamelo.cursomc.domain.Categoria;
import com.jwcamelo.cursomc.services.CategoriaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    private final CategoriaService service;

    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value="id") Integer id){
        Optional<Categoria> categoriaOptional = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaOptional.get());
    }

}