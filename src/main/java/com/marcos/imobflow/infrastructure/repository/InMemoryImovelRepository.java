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
}