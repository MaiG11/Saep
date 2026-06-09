package com.padoca.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.padoca.estoque.model.Funcionario;
import com.padoca.estoque.model.Movimentacao;
import com.padoca.estoque.model.Produto;
import com.padoca.estoque.repository.MovimentacaoRepository;
import com.padoca.estoque.repository.ProdutoRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EstoqueController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping("/estoque")
    public String carregaEstoque(HttpSession sessao, Model model) {
        if (sessao.getAttribute("funcionario") == null)
            return "redirect:/login";

        model.addAttribute("produtos", produtoRepository.findAllByOrderByNomeAsc());
        model.addAttribute("movimentacao", new Movimentacao());

        return "estoque/gestao";
    }

    @PostMapping("/estoque/salvar")
    public String cadastrarMovimentacao(@ModelAttribute Movimentacao movimentacao,
            HttpSession sessao,
            Model model,
            RedirectAttributes redirectAttributes) {
        {

            if (sessao.getAttribute("funcionario") == null)
                return "redirect:/login";

            // Vamos pegar o funcionario logado na movimentação
            Funcionario funcionario = (Funcionario) sessao.getAttribute("funcionario");
            movimentacao.setFuncionario(funcionario);

            // busca o produto pelo id que veio informado ao select do html, para depois
            // atualizar a quantidade do produto
            Produto produto = produtoRepository.findById(movimentacao.getProduto().getId()).orElseThrow();

            // Vai atualizar a quantidade de produto, somando ou subtraindo a quantidade da
            // movimentação, dependendo do tipo da movimentação
            if (movimentacao.getTipo().equals("ENTRADA"))
                produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
            else
                produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());

            // primeiro deve salvar a movimentação depois o produto, para garantir que a
            // movimentação tenha um id gerado e possa ser associada ao produto
            movimentacaoRepository.save(movimentacao);
            produtoRepository.save(produto);
            if (movimentacao.getTipo().equals("SAIDA") && produto.getQuantidade() < produto.getEstoqueMinimo()) {
                redirectAttributes.addFlashAttribute("alerta",
                        "Estoque do Produto: " + produto.getNome() +
                                " está abaixo do mínimo! Quantidade atual: " + produto.getQuantidade());
            }
            return "redirect:/estoque";
        }
    }
}
