package br.com.fiap.tarefassite.controller;

import br.com.fiap.tarefassite.domain.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping
    public String index(Model model) {
        RestTemplate api = new RestTemplate();
        List<?> tarefas = api.getForObject("http://localhost:8084/tarefas", List.class);
        model.addAttribute("tarefas", tarefas);
        return "tarefas/tarefa";
    }

    @PostMapping("/cadastrar")
    public String store(Tarefa tarefa, RedirectAttributes redirectAttributes) {
        RestTemplate api = new RestTemplate();
        tarefa.setFeita(false);
        Tarefa tarefaResultado = api.postForObject("http://localhost:8084/tarefas", tarefa, Tarefa.class);
        redirectAttributes.addFlashAttribute("msg", String.format("Tarefa \"%s\" cadastrada com sucesso.",
                tarefaResultado.getDescricao()));
        return "redirect:/tarefas";
    }
}
