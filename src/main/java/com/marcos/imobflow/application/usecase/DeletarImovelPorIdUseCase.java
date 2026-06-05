package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarImovelPorIdUseCase {

    private final ImovelRepository imovelRepository;

    public DeletarImovelPorIdUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public void executar(Long id) {
        imovelRepository.deletarPorId(id);
    }
}
