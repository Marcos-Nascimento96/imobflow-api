package com.marcos.imobflow.application.usecase;

import org.springframework.stereotype.Service;
import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;

@Service
public class CadastrarImovelUseCase {

    private final ImovelRepository imovelRepository;

    public CadastrarImovelUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public CreateImovelResponse executar(CreateImovelRequest request) {
        Imovel imovel = new Imovel(
                null,
                request.getTitulo(),
                request.getEndereco(),
                request.getValor()
        );

        imovel = imovelRepository.salvar(imovel);

        return new CreateImovelResponse(
                imovel.getId(),
                imovel.getTitulo(),
                imovel.getEndereco(),
                imovel.getValor()
        );
    }
}