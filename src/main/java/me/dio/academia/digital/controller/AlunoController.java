package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AlunoForm form) {
        Aluno obj = service.create(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> get(@PathVariable Long id) {
        Aluno aluno = service.get(id);
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento) {
        return ResponseEntity.ok().body(service.getAll(dataDeNascimento));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody AlunoUpdateForm form) {
        service.update(id, form);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
