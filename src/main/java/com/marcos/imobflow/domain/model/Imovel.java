package com.marcos.imobflow.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "imoveis")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Imovel() {
    }

    public Imovel(Long id,

                  String titulo,
                  String descricao,
                  String cidade,
                  String bairro,
                  String endereco,
                  Double valor,
                  String tipoNegociacao,
                  String tipoImovel,
                  Integer quartos,
                  Integer banheiros,
                  Integer vagasGaragem,
                  Double areaM2
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cidade = cidade;
        this.bairro = bairro;
        this.endereco = endereco;
        this.valor = valor;
        this.tipoNegociacao = tipoNegociacao;
        this.tipoImovel = tipoImovel;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.vagasGaragem = vagasGaragem;
        this.areaM2 = areaM2;
    }

    public Long getId() { return id; }
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