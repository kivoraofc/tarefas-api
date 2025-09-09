package com.example.tarefas.controller;

import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repository.TarefaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaRepository repo;
    public TarefaController(TarefaRepository repo){ this.repo = repo; }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return repo.save(tarefa);
    }

    @GetMapping
    public List<Tarefa> listar(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa buscar(@PathVariable Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa nova){
        Tarefa t = repo.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        t.setNome(nova.getNome());
        t.setDataEntrega(nova.getDataEntrega());
        t.setResponsavel(nova.getResponsavel());
        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        repo.deleteById(id);
    }
}
