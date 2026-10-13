package com.marcos.imobflow.adapters.out.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "imoveis")
public class ImovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = true, length = 255)
    private String titulo;

    @Column(name = "descricao", nullable = true, length = 255)
    private String descricao;

    @Column(name = "cidade", nullable = true, length = 255)
    private String cidade;

    @Column(name = "bairro", nullable = true, length = 255)
    private String bairro;

    @Column(name = "endereco", nullable = true, length = 255)
    private String endereco;

    @Column(name = "valor", nullable = true)
    private Double valor;

    @Column(name = "tipo_negociacao", nullable = true, length = 255)
    private String tipoNegociacao;

    @Column(name = "tipo_imovel", nullable = true, length = 255)
    private String tipoImovel;

    @Column(name = "quartos", nullable = true)
    private Integer quartos;

    @Column(name = "banheiros", nullable = true)
    private Integer banheiros;

    @Column(name = "vagas_garagem", nullable = true)
    private Integer vagasGaragem;

    @Column(name = "area_m2", nullable = true)
    private Double areaM2;

    protected ImovelEntity() {
    }

    public ImovelEntity(
            Long id,
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

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getValor() {
        return valor;
    }

    public String getTipoNegociacao() {
        return tipoNegociacao;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public Integer getQuartos() {
        return quartos;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public Integer getVagasGaragem() {
        return vagasGaragem;
    }

    public Double getAreaM2() {
        return areaM2;
    }
}
