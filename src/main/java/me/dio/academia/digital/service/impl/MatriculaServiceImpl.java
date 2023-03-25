package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import me.dio.academia.digital.service.exceptions.DataIntegrityException;
import me.dio.academia.digital.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoServiceImpl alunoService;

    @Override
    public Matricula create(MatriculaForm form) {
        Matricula matricula = new Matricula();
        matricula.setAluno(alunoService.get(form.getAlunoId()));
        return repository.save(matricula);
    }

    @Override
    public Matricula get(Long id) {
        Optional<Matricula> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Matricula.class.getName()));
    }

    @Override
    public List<Matricula> getAll(String bairro) {
        if (bairro == null) {
            return repository.findAll();
        } else {
            return repository.findByAlunoBairro(bairro);
        }
    }

    @Override
    public void delete(Long id) {
        get(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir a matrícula.");
        }
    }
}
