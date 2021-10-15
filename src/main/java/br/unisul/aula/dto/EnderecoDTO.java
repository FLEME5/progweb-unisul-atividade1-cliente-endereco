package br.unisul.aula.dto;

import br.unisul.aula.modelo.Endereco;
import br.unisul.aula.modelo.UnidadeFederativa;

public class EnderecoDTO {
    private Long idEndereco;
    private String logradouro;
    private Integer cep;
    private String bairro;
    private String cidade;
    private String uf;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Endereco endereco) {
        this.idEndereco = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf().name();
    }

    public Long getIdEndereco() {
        return this.idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getCep() {
        return this.cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Endereco converterEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(this.idEndereco);
        endereco.setLogradouro(this.logradouro);
        endereco.setCep(this.cep);
        endereco.setBairro(this.bairro);
        endereco.setCidade(this.cidade);
        endereco.setUf(UnidadeFederativa.valueOf(this.uf));
        return endereco;
    }
    
}
