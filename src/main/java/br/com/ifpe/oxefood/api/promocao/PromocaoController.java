package br.com.ifpe.oxefood.api.promocao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ifpe.oxefood.modelo.promocao.Promocao;
import br.com.ifpe.oxefood.modelo.promocao.PromocaoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/promocao")
@CrossOrigin
public class PromocaoController {

    @Autowired
    private PromocaoService promocaoService;

    @Operation(summary = "Serviço responsável por salvar uma promoção no sistema.", description = "Este endpoint recebe uma requisição contendo os dados da promoção a serem salvos e retorna a promoção salva no sistema.")
    @PostMapping
    public ResponseEntity<Promocao> save(@RequestBody PromocaoRequest request) {

        Promocao promocao = promocaoService.save(request.build());
        return new ResponseEntity<Promocao>(promocao, HttpStatus.CREATED);
    }

    @Operation(summary = "Serviço responsável por listar todos as promoções cadastradas no sistema.", description = "Este endpoint retorna uma lista contendo todas as promoções registradas no sistema.")
    @GetMapping
    public List<Promocao> listarTodos() {
        return promocaoService.listarTodos();
    }

    @Operation(summary = "Serviço responsável por obter uma promoção por ID.", description = "Este endpoint recebe o ID da promoção como parâmetro na URL e retorna a promoção correspondente, se encontrada.")
    @GetMapping("/{id}")
    public Promocao obterPorID(@PathVariable Long id) {
        return promocaoService.obterPorID(id);
    }

    @Operation(summary = "Serviço responsável por atualizar uma promoção existente no sistema.", description = "Este endpoint recebe o ID da promoção a ser atualizada na URL e os novos dados da promoção no corpo da requisição. Retorna um ResponseEntity com status 200 (OK) se a atualização for bem-sucedida.")
    @PutMapping("/{id}")
    public ResponseEntity<Promocao> update(@PathVariable("id") Long id,
            @RequestBody PromocaoRequest request) {

        promocaoService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Serviço responsável por excluir uma promoção do sistema.", description = "Este endpoint recebe o ID da promoção a ser excluída como parâmetro na URL. Retorna um ResponseEntity com status 200 (OK) se a exclusão for bem-sucedida.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        promocaoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
