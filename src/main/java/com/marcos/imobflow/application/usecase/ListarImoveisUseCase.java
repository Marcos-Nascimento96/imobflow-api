package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.ImovelFiltro;
import com.marcos.imobflow.application.dto.ImovelResponse;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarImoveisUseCase {

    private final ImovelRepository imovelRepository;

    public ListarImoveisUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public List<ImovelResponse> executar(ImovelFiltro filtro) {

        if (filtro.getValorMin() == null
                && filtro.getValorMax() == null
                && filtro.getCidade() == null) {
            return imovelRepository.listar()
                    .stream()
                    .map(ImovelResponse::fromDomain)
                    .toList();
        }

        return imovelRepository.listarPorFiltros(
                        filtro.getValorMin(),
                        filtro.getValorMax(),
                        filtro.getCidade()
                )
                .stream()
                .map(ImovelResponse::fromDomain)
                .toList();
    }
}