package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.exception.ImovelNotFoundException;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarImovelPorIdUseCase {

    private final ImovelRepository imovelRepository;

    public DeletarImovelPorIdUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public void executar(Long id) {
        Imovel imovel = imovelRepository.buscarPorId(id);

        if (imovel == null) {
            throw new ImovelNotFoundException();
        }

        imovelRepository.deletarPorId(id);
    }
}