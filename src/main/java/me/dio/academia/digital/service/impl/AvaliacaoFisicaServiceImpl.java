package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import me.dio.academia.digital.service.exceptions.DataIntegrityException;
import me.dio.academia.digital.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository repository;

    @Autowired
    private AlunoServiceImpl alunoService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacao = modelMapper.map(form, AvaliacaoFisica.class);
        avaliacao.setAluno(alunoService.get(form.getAlunoId()));
        return repository.save(avaliacao);
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        Optional<AvaliacaoFisica> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + AvaliacaoFisica.class.getName()));
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return repository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        AvaliacaoFisica avaliacao = get(id);
        modelMapper.map(formUpdate, avaliacao);
        return repository.save(avaliacao);
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

    @Override
    public List<AvaliacaoFisica> getAllByAluno(Long alunoId) {
        return repository.findAllByAluno(alunoService.get(alunoId));
    }
}
