package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl service;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MatriculaForm form) {
        Matricula obj = service.create(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Matricula> get(@PathVariable Long id) {
        Matricula matricula = service.get(id);
        return ResponseEntity.ok().body(matricula);
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll(@RequestParam(value = "bairro", required = false) String bairro) {
        return ResponseEntity.ok().body(service.getAll(bairro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
