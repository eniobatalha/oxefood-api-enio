package br.com.ifpe.oxefood.modelo.cupomDesconto;

import java.util.List;
import java.time.LocalDate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;

    @Transactional
    public Cupom save(Cupom cupom) {

        cupom.setHabilitado(Boolean.TRUE);
        cupom.setVersao(1L);
        cupom.setDataCriacao(LocalDate.now());
        return repository.save(cupom);
    }

    public List<Cupom> listarTodos() {

        return repository.findAll();
    }

    @SuppressWarnings("null")
    public Cupom obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cupom cupomAlterado) {

        @SuppressWarnings("null")
        Cupom cupom = repository.findById(id).get();
        cupom.setCodigo(cupomAlterado.getCodigo());
        cupom.setDescricao(cupomAlterado.getDescricao());
        cupom.setValorDesconto(cupomAlterado.getValorDesconto());
        cupom.setDataValidade(cupomAlterado.getDataValidade());
        cupom.setQtdMaximaUsos(cupomAlterado.getQtdMaximaUsos());
        
        cupom.setVersao(cupom.getVersao() + 1);
        repository.save(cupom);
    }

    @Transactional
    public void delete(Long id) {

        @SuppressWarnings("null")
        Cupom cupom = repository.findById(id).get();
        cupom.setHabilitado(Boolean.FALSE);
        cupom.setVersao(cupom.getVersao() + 1);
        
        repository.save(cupom);
    }
    
}
