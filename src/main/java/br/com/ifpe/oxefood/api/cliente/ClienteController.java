package br.com.ifpe.oxefood.api.cliente;

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

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
        summary = "Serviço responsável por salvar um cliente no sistema.",
        description = "Este endpoint recebe uma requisição contendo os dados do cliente a serem salvos e retorna o cliente salvo no sistema."
    )
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {

        Cliente cliente = clienteService.save(request.build());
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por listar todos os clientes cadastrados no sistema.",
        description = "Este endpoint retorna uma lista contendo todos os clientes registrados no sistema."
    )
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por obter um cliente por ID.",
        description = "Este endpoint recebe o ID do cliente como parâmetro na URL e retorna o cliente correspondente, se encontrado."
    )
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por atualizar um cliente existente no sistema.",
        description = "Este endpoint recebe o ID do cliente a ser atualizado na URL e os novos dados do cliente no corpo da requisição. Retorna um ResponseEntity com status 200 (OK) se a atualização for bem-sucedida."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, 
            @RequestBody ClienteRequest request) {

        clienteService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por excluir um cliente do sistema.",
        description = "Este endpoint recebe o ID do cliente a ser excluído como parâmetro na URL. Retorna um ResponseEntity com status 200 (OK) se a exclusão for bem-sucedida."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}
