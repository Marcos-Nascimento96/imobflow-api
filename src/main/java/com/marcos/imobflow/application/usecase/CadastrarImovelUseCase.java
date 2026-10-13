package com.marcos.imobflow.application.usecase;

import org.springframework.stereotype.Service;
import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.ImovelResponse;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;

@Service
public class CadastrarImovelUseCase {

    private final ImovelRepository imovelRepository;

    public CadastrarImovelUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public ImovelResponse executar(CreateImovelRequest request) {
        Imovel imovel = new Imovel(
                null,
                request.getTitulo(),
                request.getDescricao(),
                request.getCidade(),
                request.getBairro(),
                request.getEndereco(),
                request.getValor(),
                request.getTipoNegociacao(),
                request.getTipoImovel(),
                request.getQuartos(),
                request.getBanheiros(),
                request.getVagasGaragem(),
                request.getAreaM2()
        );

        imovel = imovelRepository.salvar(imovel);

        return ImovelResponse.fromDomain(imovel);
    }
}