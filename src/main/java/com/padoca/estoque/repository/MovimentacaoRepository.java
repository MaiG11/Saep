package com.padoca.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.padoca.estoque.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
    