package com.padoca.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padoca.estoque.model.Produto;
import com.padoca.estoque.repository.ProdutoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProdutoController {

    private final PrincipalController principalController;
    @Autowired
    private ProdutoRepository produtoRepository;

    ProdutoController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    @GetMapping("/produtos")
    public String listarProdutos(HttpSession sessao, Model model, @RequestParam(required = false) String buscaProduto){
        if (sessao.getAttribute("funcionario") ==null)
            return "redirect:/login";

        List<Produto> produtos;
        if (buscaProduto != null && !buscaProduto.isBlank()){
         produtos = produtoRepository.findByNomeContainingIgnoreCase(buscaProduto);
        
    }else{
        produtos = produtoRepository.findAll();
    }
        model.addAttribute("produtos", produtos);
        return "produtos/lista";  
    }

     @PostMapping("/produtos/salvar")
     public String salvarProduto(HttpSession sessao, @ModelAttribute Produto produto){
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";
           
        produtoRepository.save(produto);

         return "redirect:/produtos";
     
    }
    @PostMapping("/produtos/deletar/{id}")
    public String deletarProduto(HttpSession sessao, @PathVariable Long id){ // @PathVariable para pegar um valor dinamico da URL, nesse caso o id do produto a ser deletado
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";

        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
}
