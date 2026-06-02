package com.marcos.imobflow.infrastructure.repository;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class MySqlImovelRepository implements ImovelRepository {

    private final JpaImovelRepository jpaImovelRepository;

    public MySqlImovelRepository(JpaImovelRepository jpaImovelRepository) {
        this.jpaImovelRepository = jpaImovelRepository;
    }

    @Override
    public Imovel salvar(Imovel imovel) {
        return jpaImovelRepository.save(imovel);
    }

    @Override
    public List<Imovel> listar() {
        return jpaImovelRepository.findAll();
    }
}