package com.marcos.imobflow.application.dto;

public class CreateImovelResponse {

    private Long id;
    private String titulo;
    private String endereco;
    private Double valor;

    public CreateImovelResponse(Long id, String titulo, String endereco, Double valor) {
        this.id = id;
        this.titulo = titulo;
        this.endereco = endereco;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getValor() {
        return valor;
    }
}

