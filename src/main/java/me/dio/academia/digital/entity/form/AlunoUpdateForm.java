package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoUpdateForm {

  @Size(min = 3, max = 50, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres")
  private String nome;

  @Size(min = 3, max = 50, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres")
  private String bairro;

  @Past(message = "Data '${validatedValue}' é inválida")
  private LocalDate dataDeNascimento;
}
