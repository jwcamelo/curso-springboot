package com.jwcamelo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwcamelo.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
