package com.padoca.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.padoca.estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
    