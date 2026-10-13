package com.marcos.imobflow.adapters.out.repository;

import com.marcos.imobflow.domain.model.Imovel;

public final class ImovelEntityMapper {

    private ImovelEntityMapper() {
    }

    public static ImovelEntity toEntity(Imovel imovel) {
        if (imovel == null) {
            return null;
        }

        return new ImovelEntity(
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

    public static Imovel toDomain(ImovelEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Imovel(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getCidade(),
                entity.getBairro(),
                entity.getEndereco(),
                entity.getValor(),
                entity.getTipoNegociacao(),
                entity.getTipoImovel(),
                entity.getQuartos(),
                entity.getBanheiros(),
                entity.getVagasGaragem(),
                entity.getAreaM2()
        );
    }
}
