package com.jwcamelo.cursomc.services;

import com.jwcamelo.cursomc.domain.Cliente;
import com.jwcamelo.cursomc.domain.Cliente;
import com.jwcamelo.cursomc.dto.ClienteDTO;
import com.jwcamelo.cursomc.repositories.ClienteRepository;
import com.jwcamelo.cursomc.services.exceptions.DataIntegrityException;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId()).get();
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.delete(find(id).get());
        }catch(DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
        }

    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO (ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null,null);
    }

}
