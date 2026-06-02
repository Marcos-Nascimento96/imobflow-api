package com.marcos.imobflow.application.dto;

public class CreateImovelRequest {

    private String titulo;
    private String descricao;
    private String cidade;
    private String bairro;
    private String endereco;
    private Double valor;
    private String tipoNegociacao;
    private String tipoImovel;
    private Integer quartos;
    private Integer banheiros;
    private Integer vagasGaragem;
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