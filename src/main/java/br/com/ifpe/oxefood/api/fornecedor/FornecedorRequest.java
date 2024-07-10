package br.com.ifpe.oxefood.api.fornecedor;
import br.com.ifpe.oxefood.modelo.fornecedor.Fornecedor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorRequest {

    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private String endereco;

    private String email;

    private String telefoneCelular;

    private String telefoneFixo;

    public Fornecedor build() {

        return Fornecedor.builder()
            .razaoSocial(razaoSocial)
            .nomeFantasia(nomeFantasia)
            .cnpj(cnpj)
            .endereco(endereco)
            .email(email)
            .telefoneCelular(telefoneCelular)
            .telefoneFixo(telefoneFixo)
            .build();
    }
}
