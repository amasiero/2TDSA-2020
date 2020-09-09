package br.com.fiap.tarefassite.controller;

import br.com.fiap.tarefassite.domain.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/feita/{id}")
    public String done(@PathVariable("id") long codigo, RedirectAttributes redirectAttributes) {
        RestTemplate template = new RestTemplate();

        Tarefa tarefa = template.getForObject("http://localhost:8084/tarefas/" + codigo, Tarefa.class);
        tarefa.setFeita(!tarefa.getFeita());

        if(tarefa.getDataLimite() == null || tarefa.getDataLimite().isBefore(LocalDate.now())) {
            tarefa.setDataLimite(LocalDate.now());
        }

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(codigo));

        template.put("http://localhost:8084/tarefas/{id}", tarefa, params);

        redirectAttributes.addFlashAttribute("msg", tarefa.getFeita() ?
                "Tarefa realizada com sucesso!" :
                "Tarefa voltou para a lista.");
        return "redirect:/tarefas";
    }

    @PostMapping("/excluir")
    public String delete(long codigo, RedirectAttributes redirectAttributes) {
        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(codigo));

        template.delete("http://localhost:8084/tarefas/{id}", params);

        redirectAttributes.addFlashAttribute("msg", "Tarefa excluída com sucesso!");
        return "redirect:/tarefas";
    }
}












