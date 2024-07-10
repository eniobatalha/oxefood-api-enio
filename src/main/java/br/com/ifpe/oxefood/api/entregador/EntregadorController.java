package br.com.ifpe.oxefood.api.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/entregador")
@CrossOrigin
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @Operation(
        summary = "Serviço responsável por salvar um entregador no sistema.",
        description = "Este endpoint recebe uma requisição contendo os dados do entregador a serem salvos e retorna o entregador salvo no sistema."
    )
    @PostMapping
    public ResponseEntity<Entregador> save(@RequestBody EntregadorRequest request) {

        Entregador entregador = entregadorService.save(request.build());
        return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por listar todos os entregadores cadastrados no sistema.",
        description = "Este endpoint retorna uma lista contendo todos os entregadores registrados no sistema."
    )
    @GetMapping
    public List<Entregador> listarTodos() {
        return entregadorService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por obter um entregador por ID.",
        description = "Este endpoint recebe o ID do entregador como parâmetro na URL e retorna o entregador correspondente, se encontrado."
    )
    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable Long id) {
        return entregadorService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por atualizar um entregador existente no sistema.",
        description = "Este endpoint recebe o ID do entregador a ser atualizado na URL e os novos dados do entregador no corpo da requisição. Retorna um ResponseEntity com status 200 (OK) se a atualização for bem-sucedida."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Entregador> update(@PathVariable("id") Long id, 
            @RequestBody EntregadorRequest request) {

        entregadorService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por excluir um entregador do sistema.",
        description = "Este endpoint recebe o ID do entregador a ser excluído como parâmetro na URL. Retorna um ResponseEntity com status 200 (OK) se a exclusão for bem-sucedida."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entregadorService.delete(id);
        return ResponseEntity.ok().build();
    }

}
