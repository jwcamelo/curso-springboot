package com.jwcamelo.cursomc.resources;

import com.jwcamelo.cursomc.domain.Categoria;
import com.jwcamelo.cursomc.domain.Pedido;
import com.jwcamelo.cursomc.dto.CategoriaDTO;
import com.jwcamelo.cursomc.services.PedidoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

    private final PedidoService service;

    public PedidoResource(PedidoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value="id") Integer id){
        Optional<Pedido> pedidoOptional = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional.get());
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
    	obj = service.insert(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).build();
    }

}
