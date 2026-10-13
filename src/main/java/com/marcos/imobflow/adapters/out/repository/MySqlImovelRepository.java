package com.marcos.imobflow.adapters.out.repository;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlImovelRepository implements ImovelRepository {

    private final JpaImovelRepository jpaImovelRepository;

    public MySqlImovelRepository(JpaImovelRepository jpaImovelRepository) {
        this.jpaImovelRepository = jpaImovelRepository;
    }

    @Override
    public Imovel salvar(Imovel imovel) {
        ImovelEntity entitySalva = jpaImovelRepository.save(ImovelEntityMapper.toEntity(imovel));
        return ImovelEntityMapper.toDomain(entitySalva);
    }

    @Override
    public List<Imovel> listar() {
        return toDomainList(jpaImovelRepository.findAll());
    }

    @Override
    public List<Imovel> listarPorFiltros(
            Double valorMin,
            Double valorMax,
            String cidade
    ) {
        Specification<ImovelEntity> spec = Specification.unrestricted();

        if (cidade != null) {
            spec = spec.and(ImovelSpecification.cidadeIgual(cidade));
        }

        if (valorMin != null) {
            spec = spec.and(ImovelSpecification.valorMaiorOuIgual(valorMin));
        }

        if (valorMax != null) {
            spec = spec.and(ImovelSpecification.valorMenorOuIgual(valorMax));
        }

        return toDomainList(jpaImovelRepository.findAll(spec));
    }

    @Override
    public Imovel buscarPorId(Long id) {
        return jpaImovelRepository.findById(id)
                .map(ImovelEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deletarPorId(Long id) {
        jpaImovelRepository.deleteById(id);
    }

    @Override
    public Imovel atualizar(Imovel imovel) {
        ImovelEntity entityAtualizada = jpaImovelRepository.save(ImovelEntityMapper.toEntity(imovel));
        return ImovelEntityMapper.toDomain(entityAtualizada);
    }

    private List<Imovel> toDomainList(List<ImovelEntity> entities) {
        return entities.stream()
                .map(ImovelEntityMapper::toDomain)
                .toList();
    }
}
