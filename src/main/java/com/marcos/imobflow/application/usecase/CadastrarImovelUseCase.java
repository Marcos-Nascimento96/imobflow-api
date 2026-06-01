package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.domain.model.Imovel;

public class CadastrarImovelUseCase {

    public Imovel executar(CreateImovelRequest request) {
        return new Imovel(
                null,
                request.getTitulo(),
                request.getEndereco(),
                request.getValor()
        );
    }
}