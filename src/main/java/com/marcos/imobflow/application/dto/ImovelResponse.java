package com.marcos.imobflow.application.dto;

import com.marcos.imobflow.domain.model.Imovel;

public record ImovelResponse(
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

    public static ImovelResponse fromDomain(Imovel imovel) {
        if (imovel == null) {
            return null;
        }
        return new ImovelResponse(
                imovel.getId(),
                imovel.getTitulo(),
                imovel.getDescricao(),
                imovel.getCidade(),
                imovel.getBairro(),
                imovel.getEndereco(),
                imovel.getValor(),
                imovel.getTipoNegociacao(),
                imovel.getTipoImovel(),
                imovel.getQuartos(),
                imovel.getBanheiros(),
                imovel.getVagasGaragem(),
                imovel.getAreaM2()
        );
    }

}
