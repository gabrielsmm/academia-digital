package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.exceptions.DataIntegrityException;
import me.dio.academia.digital.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = modelMapper.map(form, Aluno.class);
        try {
            return repository.save(aluno);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível inserir, CPF já cadastrado.");
        }
    }

    @Override
    public Aluno get(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if (dataDeNascimento == null) {
            return repository.findAll();
        } else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataDeNascimento(localDate);
        }
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = get(id);
        modelMapper.map(formUpdate, aluno);
        return repository.save(aluno);
    }

    @Override
    public void delete(Long id) {
        get(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir.");
        }
    }
}
