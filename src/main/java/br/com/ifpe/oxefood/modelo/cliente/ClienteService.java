package br.com.ifpe.oxefood.modelo.cliente;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import br.com.ifpe.oxefood.util.exception.ClienteException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Cliente save(Cliente cliente) {

        Cliente clienteConsultado = repository.findByNome(cliente.getNome());
        if (clienteConsultado != null) {
            throw new ClienteException(ClienteException.MSG_NOME_DUPLICADO);
        }

        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        Cliente clienteSalvo = repository.save(cliente);
        emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);
        return clienteSalvo;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    public Cliente obterPorID(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {
        Cliente cliente = repository.findById(id).get();
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());

        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {
        Cliente cliente = repository.findById(id).get();
        cliente.setHabilitado(Boolean.FALSE);
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    @Transactional
    public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {
        Cliente cliente = this.obterPorID(clienteId);

        // Primeiro salva o EnderecoCliente:

        endereco.setCliente(cliente);
        endereco.setHabilitado(Boolean.TRUE);
        enderecoClienteRepository.save(endereco);

        // Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

        List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();

        if (listaEnderecoCliente == null) {
            listaEnderecoCliente = new ArrayList<EnderecoCliente>();
        }

        listaEnderecoCliente.add(endereco);
        cliente.setEnderecos(listaEnderecoCliente);
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);

        return endereco;
    }

    @Transactional
    public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {
        EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
        endereco.setRua(enderecoAlterado.getRua());
        endereco.setNumero(enderecoAlterado.getNumero());
        endereco.setBairro(enderecoAlterado.getBairro());
        endereco.setCep(enderecoAlterado.getCep());
        endereco.setCidade(enderecoAlterado.getCidade());
        endereco.setEstado(enderecoAlterado.getEstado());
        endereco.setComplemento(enderecoAlterado.getComplemento());

        return enderecoClienteRepository.save(endereco);
    }

    @Transactional
    public void removerEnderecoCliente(Long id) {
        EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
        endereco.setHabilitado(Boolean.FALSE);
        enderecoClienteRepository.save(endereco);

        Cliente cliente = this.obterPorID(endereco.getCliente().getId());
        cliente.getEnderecos().remove(endereco);
        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    // Método para obter um endereço de um cliente por ID
    public EnderecoCliente obterEnderecoPorClienteId(Long clienteId) {
        List<EnderecoCliente> enderecos = enderecoClienteRepository.findByClienteId(clienteId);
        if (!enderecos.isEmpty()) {
            return enderecos.get(0); // ou alguma lógica para escolher o endereço desejado
        }
        return null;
    }

    // Método para obter todos os endereços de um cliente
    public List<EnderecoCliente> listarEnderecosPorClienteId(Long clienteId) {
        return enderecoClienteRepository.findByClienteId(clienteId);
    }
}
