package br.com.fiap.techchallenge.lanchonete.dtos.inbound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ClienteDTO {

    @NotNull(message = "Nome é obrigatório.")
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "E-mail é obrigatório.")
    @NotBlank(message = "E-mail é obrigatório.")
    private String email;

    @NotNull(message = "CPF é obrigatório.")
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;
}
