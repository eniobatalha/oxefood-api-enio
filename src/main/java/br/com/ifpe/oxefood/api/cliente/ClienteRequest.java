package br.com.ifpe.oxefood.api.cliente;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotNull(message = "O nome do cliente é de preenchimento obrigatório.")
    @NotBlank(message = "O nome do cliente é de preenchimento obrigatório.")
    @Length(max = 100, message = "O nome do cliente deve ter no máximo {max} caracteres.")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "O CPF do cliente é de preenchimento obrigatório.")
    @NotBlank(message = "O CPF do cliente é de preenchimento obrigatório.")
    @CPF
    private String cpf;

    private String foneCelular;

    @Length(min = 8, max = 20, message = "O telefone fixo deve ter entre {min} e {max} caracteres.")
    private String foneFixo;

    public Cliente build() {

        return Cliente.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .cpf(cpf)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .build();
    }

}
