package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AvaliacaoFisicaForm form) {
        AvaliacaoFisica obj = service.create(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AvaliacaoFisica> get(@PathVariable Long id) {
        AvaliacaoFisica avaliacao = service.get(id);
        return ResponseEntity.ok().body(avaliacao);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoFisica>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody AvaliacaoFisicaUpdateForm form) {
        service.update(id, form);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/aluno/{alunoId}")
    public ResponseEntity<List<AvaliacaoFisica>> getAllByAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok().body(service.getAllByAluno(alunoId));
    }

}
