package com.marcos.imobflow.application.dto;

public class CreateImovelRequest {

    private String titulo;
    private String endereco;
    private Double valor;

    public CreateImovelRequest() {
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