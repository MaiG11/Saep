package com.padoca.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.padoca.estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //buscar produtos onde o campo contem algo(o que for enviado conmo parametro)
    // SELLECT * FROM produto WHERE nome LIKE '%sNome%'
    List<Produto> findByNomeContainingIgnoreCase(String sNome);

    //SELLECT *FROM produto ORDER BY nome ASC- ordem alfabética crescente
    List<Produto> findAllByOrderByNomeAsc();
}


    