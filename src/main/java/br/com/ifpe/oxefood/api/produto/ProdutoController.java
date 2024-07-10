package br.com.ifpe.oxefood.api.produto;

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
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(
        summary = "Serviço responsável por salvar um produto no sistema.",
        description = "Este endpoint recebe uma requisição contendo os dados do produto a serem salvos e retorna o produto salvo no sistema."
    )
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoRequest request) {

        Produto produto = produtoService.save(request.build());
        return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por listar todos os produtos cadastrados no sistema.",
        description = "Este endpoint retorna uma lista contendo todos os produtos registrados no sistema."
    )
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por obter um produto por ID.",
        description = "Este endpoint recebe o ID do produto como parâmetro na URL e retorna o produto correspondente, se encontrado."
    )
    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por atualizar um produto existente no sistema.",
        description = "Este endpoint recebe o ID do produto a ser atualizado na URL e os novos dados do produto no corpo da requisição. Retorna um ResponseEntity com status 200 (OK) se a atualização for bem-sucedida."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id,
            @RequestBody ProdutoRequest request) {

        produtoService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por excluir um produto do sistema.",
        description = "Este endpoint recebe o ID do produto a ser excluído como parâmetro na URL. Retorna um ResponseEntity com status 200 (OK) se a exclusão for bem-sucedida."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
