package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.exception.ImovelNotFoundException;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarImovelPorIdUseCase {

    private final ImovelRepository imovelRepository;

    public BuscarImovelPorIdUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public Imovel executar(Long id) {
        Imovel imovel = imovelRepository.buscarPorId(id);

        if (imovel == null) {
            throw new ImovelNotFoundException();
        }

        return imovel;
    }
}