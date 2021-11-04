package br.unisul.aula.dto;

import java.util.List;

import br.unisul.aula.modelo.UnidadeFederativa;

public class ClientesCidadeDTO {  
    private String cidade;
    private UnidadeFederativa uf;
    private List<ClienteDTO> clientes;

    public ClientesCidadeDTO() {
    }

    public ClientesCidadeDTO(String cidade, UnidadeFederativa uf, List<ClienteDTO> clientes) {
        this.cidade = cidade;
        this.uf = uf;
        this.clientes = clientes;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UnidadeFederativa getUf() {
        return this.uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public List<ClienteDTO> getClientes() {
        return this.clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

}
