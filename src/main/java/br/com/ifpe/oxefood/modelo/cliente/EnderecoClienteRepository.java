package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long>{
    
    List<EnderecoCliente> findByClienteId(Long clienteId);
    
}
