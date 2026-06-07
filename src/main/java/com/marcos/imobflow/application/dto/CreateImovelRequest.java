package com.marcos.imobflow.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateImovelRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String cidade;

    @NotBlank
    private String bairro;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private Double valor;

    @NotBlank
    private String tipoNegociacao;

    @NotBlank
    private String tipoImovel;

    @NotNull
    @PositiveOrZero
    private Integer quartos;

    @NotNull
    @PositiveOrZero
    private Integer banheiros;

    @NotNull
    @PositiveOrZero
    private Integer vagasGaragem;

    @NotNull
    @Positive
    private Double areaM2;

    public CreateImovelRequest() {
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getEndereco() { return endereco; }
    public Double getValor() { return valor; }
    public String getTipoNegociacao() { return tipoNegociacao; }
    public String getTipoImovel() { return tipoImovel; }
    public Integer getQuartos() { return quartos; }
    public Integer getBanheiros() { return banheiros; }
    public Integer getVagasGaragem() { return vagasGaragem; }
    public Double getAreaM2() { return areaM2; }
}