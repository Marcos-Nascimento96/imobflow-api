package com.marcos.imobflow.application.dto;

public class ImovelFiltro {

    private Double valorMin;
    private Double valorMax;
    private String cidade;

    public ImovelFiltro(Double valorMin, Double valorMax, String cidade) {
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.cidade = cidade;
    }

    public Double getValorMin() {
        return valorMin;
    }

    public Double getValorMax() {
        return valorMax;
    }

    public String getCidade() {
        return cidade;
    }
}
