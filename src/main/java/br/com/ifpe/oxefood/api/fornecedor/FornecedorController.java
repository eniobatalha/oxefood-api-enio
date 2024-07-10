package br.com.ifpe.oxefood.api.fornecedor;

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
import br.com.ifpe.oxefood.modelo.fornecedor.Fornecedor;
import br.com.ifpe.oxefood.modelo.fornecedor.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/fornecedor")
@CrossOrigin
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @Operation(
        summary = "Serviço responsável por salvar um fornecedor no sistema.",
        description = "Este endpoint recebe uma requisição contendo os dados do fornecedor a serem salvos e retorna o fornecedor salvo no sistema."
    )
    @PostMapping
    public ResponseEntity<Fornecedor> save(@RequestBody FornecedorRequest request) {

        Fornecedor fornecedor = fornecedorService.save(request.build());
        return new ResponseEntity<Fornecedor>(fornecedor, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por listar todos os fornecedores cadastrados no sistema.",
        description = "Este endpoint retorna uma lista contendo todos os fornecedores registrados no sistema."
    )
    @GetMapping
    public List<Fornecedor> listarTodos() {
        return fornecedorService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por obter um fornecedor por ID.",
        description = "Este endpoint recebe o ID do fornecedor como parâmetro na URL e retorna o fornecedor correspondente, se encontrado."
    )
    @GetMapping("/{id}")
    public Fornecedor obterPorID(@PathVariable Long id) {
        return fornecedorService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por atualizar um fornecedor existente no sistema.",
        description = "Este endpoint recebe o ID do fornecedor a ser atualizado na URL e os novos dados do fornecedor no corpo da requisição. Retorna um ResponseEntity com status 200 (OK) se a atualização for bem-sucedida."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable("id") Long id, 
            @RequestBody FornecedorRequest request) {

        fornecedorService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por excluir um fornecedor do sistema.",
        description = "Este endpoint recebe o ID do fornecedor a ser excluído como parâmetro na URL. Retorna um ResponseEntity com status 200 (OK) se a exclusão for bem-sucedida."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.ok().build();
    }

}
