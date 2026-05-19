package com.padoca.estoque.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimentacao {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "produto_id", nullable = false)
private Produto produto;

@ManyToOne
@JoinColumn(name = "funcionario_id", nullable = false)
private Funcionario funcionario;

private String tipo;
private Integer quantidade;
private LocalDate data;


public Long getId() {
    return id;
}
public Produto getProduto() {
    return produto;
}
public void setProduto(Produto produto) {
    this.produto = produto;
}
public Funcionario getFuncionario() {
    return funcionario;
}
public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
}
public String getTipo() {
    return tipo;
}
public void setTipo(String tipo) {
    this.tipo = tipo;
}
public Integer getQuantidade() {
    return quantidade;
}
public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
}
public LocalDate getData() {
    return data;
}
public void setData(LocalDate data) {
    this.data = data;
}
   
}
