package com.jwcamelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jwcamelo.cursomc.domain.Categoria;
import com.jwcamelo.cursomc.domain.Produto;
import com.jwcamelo.cursomc.repositories.CategoriaRepository;
import com.jwcamelo.cursomc.repositories.ProdutoRepository;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	private final ProdutoRepository repo;
	private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository repo, CategoriaRepository categoriaRepository) {
        this.repo = repo;
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Produto> find(Integer id){
    	Optional<Produto> produto = repo.findById(id);
    	if(!produto.isPresent())
    		throw new ObjectNotFoundException("Objeto não encontrado: Id: "+id+" , Tipo: "+Produto.class);
    	return produto;
    }
    
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    	
    }

}
