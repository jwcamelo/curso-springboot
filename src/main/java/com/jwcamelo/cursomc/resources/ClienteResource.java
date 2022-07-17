package com.jwcamelo.cursomc.resources;

import com.jwcamelo.cursomc.domain.Cliente;
import com.jwcamelo.cursomc.services.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value="id") Integer id){
        Optional<Cliente> ClienteOptional = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(ClienteOptional.get());
    }

}
