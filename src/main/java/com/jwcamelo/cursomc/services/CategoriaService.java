package com.jwcamelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.jwcamelo.cursomc.domain.Cliente;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jwcamelo.cursomc.domain.Categoria;
import com.jwcamelo.cursomc.dto.CategoriaDTO;
import com.jwcamelo.cursomc.repositories.CategoriaRepository;
import com.jwcamelo.cursomc.services.exceptions.DataIntegrityException;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

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

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId()).get();
		updateData(newObj,obj);
		return repo.save(newObj);
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(find(id).get());
		}catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO (CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

}
