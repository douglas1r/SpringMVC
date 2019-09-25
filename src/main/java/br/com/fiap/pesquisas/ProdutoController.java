package br.com.fiap.pesquisas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping("cadastrar")
    public String abrirFormulario(Produto produto, Model model){
        model.addAttribute("prod", produto);
        return "produto/form";

    }

    @PostMapping("cadastrar")
    public String processarForm(Produto produto, Model model){
        repository.save(produto);
        model.addAttribute("prod", produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("listar")
    public String listarProdutos(Model model){
        model.addAttribute("produtos", repository.findAll());
        return "produto/lista";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") int codigo, Model model){
        model.addAttribute("prod",repository.findById(codigo));
        return "produto/form";
    }

}
