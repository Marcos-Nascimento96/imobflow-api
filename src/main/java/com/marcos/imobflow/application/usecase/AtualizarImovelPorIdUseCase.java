package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Service;
import com.marcos.imobflow.application.exception.ImovelNotFoundException;

@Service
public class AtualizarImovelPorIdUseCase {

    private final ImovelRepository imovelRepository;

    public AtualizarImovelPorIdUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public CreateImovelResponse executar(Long id, CreateImovelRequest request) {
        Imovel imovelExistente = imovelRepository.buscarPorId(id);

        if (imovelExistente == null) {
            throw new ImovelNotFoundException();
        }

        Imovel imovelAtualizado = new Imovel(
                id,
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

        imovelAtualizado = imovelRepository.atualizar(imovelAtualizado);

        return new CreateImovelResponse(
                imovelAtualizado.getId(),
                imovelAtualizado.getTitulo(),
                imovelAtualizado.getDescricao(),
                imovelAtualizado.getCidade(),
                imovelAtualizado.getBairro(),
                imovelAtualizado.getEndereco(),
                imovelAtualizado.getValor(),
                imovelAtualizado.getTipoNegociacao(),
                imovelAtualizado.getTipoImovel(),
                imovelAtualizado.getQuartos(),
                imovelAtualizado.getBanheiros(),
                imovelAtualizado.getVagasGaragem(),
                imovelAtualizado.getAreaM2()
        );
    }
}