package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaUpdateForm {

  @NotNull(message = "Preencha o campo corretamente")
  private Double peso;

  @NotNull(message = "Preencha o campo corretamente")
  private Double altura;
}
