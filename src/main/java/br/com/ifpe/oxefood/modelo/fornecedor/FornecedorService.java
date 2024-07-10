package br.com.ifpe.oxefood.modelo.fornecedor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Transactional
    public Fornecedor save(Fornecedor fornecedor) {

        fornecedor.setHabilitado(Boolean.TRUE);
        fornecedor.setVersao(1L);
        fornecedor.setDataCriacao(LocalDate.now());
        return repository.save(fornecedor);
    }

    public List<Fornecedor> listarTodos() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    public Fornecedor obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Fornecedor fornecedorAlterado) {

        @SuppressWarnings("null")
        Fornecedor fornecedor = repository.findById(id).get();
        fornecedor.setRazaoSocial(fornecedorAlterado.getRazaoSocial());
        fornecedor.setNomeFantasia(fornecedorAlterado.getNomeFantasia());
        fornecedor.setCnpj(fornecedorAlterado.getCnpj());
        fornecedor.setEndereco(fornecedorAlterado.getEndereco());
        fornecedor.setEmail(fornecedorAlterado.getEmail());
        fornecedor.setTelefoneCelular(fornecedorAlterado.getTelefoneCelular());
        fornecedor.setTelefoneFixo(fornecedorAlterado.getTelefoneFixo());
        fornecedor.setVersao(fornecedor.getVersao() + 1);
        repository.save(fornecedor);
    }

    @Transactional
    public void delete(Long id) {

        @SuppressWarnings("null")
        Fornecedor fornecedor = repository.findById(id).get();
        fornecedor.setHabilitado(Boolean.FALSE);
        fornecedor.setVersao(fornecedor.getVersao() + 1);
        
        repository.save(fornecedor);
    }

}
