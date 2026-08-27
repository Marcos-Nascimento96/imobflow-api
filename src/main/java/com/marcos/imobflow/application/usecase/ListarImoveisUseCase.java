package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarImoveisUseCase {

    private final ImovelRepository imovelRepository;

    public ListarImoveisUseCase(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    public List<Imovel> executar(Double valorMin, Double valorMax) {

        if (valorMin == null && valorMax == null) {
            return imovelRepository.listar();
        }

        return imovelRepository.listarPorFaixaDeValor(valorMin, valorMax);
    }
}

