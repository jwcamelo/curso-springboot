package com.jwcamelo.cursomc.cursomc.repositories;

import com.jwcamelo.cursomc.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
