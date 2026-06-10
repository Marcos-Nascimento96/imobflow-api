package com.marcos.imobflow.infrastructure.repository;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryImovelRepository implements ImovelRepository {

    private final List<Imovel> imoveis = new ArrayList<>();

    @Override
    public Imovel salvar(Imovel imovel) {
        imoveis.add(imovel);
        return imovel;
    }

    @Override
    public List<Imovel> listar() {
        return imoveis;
    }

    @Override
    public Imovel buscarPorId(Long id) {
        return imoveis.stream()
                .filter(imovel -> imovel.getId() != null && imovel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deletarPorId(Long id) {
        imoveis.removeIf(imovel -> imovel.getId() != null && imovel.getId().equals(id));
    }
    @Override
    public Imovel atualizar(Imovel imovel) {
        deletarPorId(imovel.getId());
        imoveis.add(imovel);
        return imovel;
    }
}

