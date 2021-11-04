package br.unisul.aula.dto;

import br.unisul.aula.modelo.Cliente;
import br.unisul.aula.modelo.Endereco;

public class ClienteDTO {
    private Long idCliente;
    private String nome;
    private String complemento;
    private Integer numero;
    private EnderecoDTO endereco;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getId();
        this.nome = cliente.getNome();
        this.complemento = cliente.getComplemento();
        this.endereco = new EnderecoDTO(cliente.getEndereco());
    }

    public Long getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public EnderecoDTO getEndereco() {
        return this.endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
    
    public Cliente converterCliente(Endereco endereco) {
        Cliente cliente = new Cliente();
        cliente.setId(this.idCliente);
        cliente.setNome(this.nome);
        cliente.setComplemento(this.complemento);
        cliente.setNumero(this.numero);
        cliente.setEndereco(endereco);
        return cliente;
    }

    public ClienteDTO clientesCidade (Cliente cliente) {
        ClienteDTO clienteCidade = new ClienteDTO();
        this.idCliente = cliente.getId();
        this.nome = cliente.getNome();
        return clienteCidade;
    }
}
