

package com.padoca.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.padoca.estoque.model.Produto;
import com.padoca.estoque.repository.ProdutoRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos/novo")
    public String novoRegistro(HttpSession sessao, Model model){
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";

        model.addAttribute("produto", new Produto());
        //Vai abrir o mesmo formulário para edição /inclusão, mas sem os dados preenchidos, pois é um novo produto
        return "produtos/formulario";
    }
    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(
        HttpSession sessao, @PathVariable Long id, Model model){

            if (sessao.getAttribute("funcionario") == null)
                return "redirect:/login";

            Produto produto = produtoRepository.findById(id).orElseThrow();
            model.addAttribute("produto", produto);
            return "produtos/formulario";
        }
    
    @GetMapping("/produtos")
    public String listarProdutos(
        HttpSession sessao, 
        Model model, 
        @RequestParam(required = false) String buscaProduto) {
            
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";

        List<Produto> produtos;
        if (buscaProduto != null && !buscaProduto.isBlank())
            produtos = produtoRepository.findByNomeContainingIgnoreCase(buscaProduto);
        else
            produtos = produtoRepository.findAll();

        model.addAttribute("produtos", produtos);

        return "produtos/lista";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(
        HttpSession sessao, 
       @Valid @ModelAttribute Produto produto, BindingResult errosAcumulados,// Classe responsável por encontrar todos os erros de validação do produto
        Model model 
       ){
            
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";

        if (errosAcumulados.hasErrors()){
            model.addAttribute("produto", produto);
            model.addAttribute("produtos", produtoRepository.findAll());
            return "produtos/formulario";
        }
        
        produtoRepository.save(produto);

        return "redirect:/produtos";
    }
    @PostMapping("/produtos/deletar/{id}")
    public String deletarProduto(HttpSession sessao, @PathVariable Long id){
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";

        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
    
}

