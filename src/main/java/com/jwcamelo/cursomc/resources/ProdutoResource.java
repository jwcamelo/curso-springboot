package com.jwcamelo.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwcamelo.cursomc.domain.Produto;
import com.jwcamelo.cursomc.dto.CategoriaDTO;
import com.jwcamelo.cursomc.dto.ProdutoDTO;
import com.jwcamelo.cursomc.resources.utils.URL;
import com.jwcamelo.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

    private final ProdutoService service;

    public ProdutoResource(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value="id") Integer id){
        Optional<Produto> produtoOptional = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }
    
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
    		@RequestParam(value="nome", defaultValue="") String nome,
    		@RequestParam(value="categorias", defaultValue="") String categorias,
    		@RequestParam(value="page", defaultValue="0") Integer page,
    		@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
    		@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
    		@RequestParam(value="direction", defaultValue="ASC") String direction){
    	String nomeDecoded = URL.decodeParam(nome);
    	List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
