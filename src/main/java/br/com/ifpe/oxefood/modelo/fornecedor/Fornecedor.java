package br.com.ifpe.oxefood.modelo.fornecedor;

import org.hibernate.annotations.SQLRestriction;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Fornecedor")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Fornecedor extends EntidadeAuditavel {

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;

    @Column /*(unique = true) podemos usar essa restrição se necessário*/
    private String cnpj;

    @Column
    private String endereco;

    @Column
    private String email;

    @Column
    private String telefoneCelular;

    @Column
    private String telefoneFixo;

}
