package com.marcos.imobflow.application.usecase;

import org.springframework.stereotype.Service;
import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.domain.model.Imovel;

@Service
public class CadastrarImovelUseCase {

    public CreateImovelResponse executar(CreateImovelRequest request) {
        Imovel imovel = new Imovel(
                null,
                request.getTitulo(),
                request.getEndereco(),
                request.getValor()
        );

        return new CreateImovelResponse(
                imovel.getId(),
                imovel.getTitulo(),
                imovel.getEndereco(),
                imovel.getValor()
        );
    }
}