package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {

    public List<AvaliacaoFisica> findAllByAluno(Aluno aluno);
}
