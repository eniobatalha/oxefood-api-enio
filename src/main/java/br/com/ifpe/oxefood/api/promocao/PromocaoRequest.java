package br.com.ifpe.oxefood.api.promocao;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.promocao.Promocao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class PromocaoRequest {

    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    private String regra;

    private double valorDesconto;

    public Promocao build() {

        return Promocao.builder()
            .titulo(titulo)
            .dataInicio(dataInicio)
            .dataFim(dataFim)
            .regra(regra)
            .valorDesconto(valorDesconto)
            .build();

    }
    
}
