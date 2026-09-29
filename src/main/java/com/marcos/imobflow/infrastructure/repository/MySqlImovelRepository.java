package com.marcos.imobflow.infrastructure.repository;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;

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

    @Override
    public List<Imovel> listarPorFaixaDeValor(Double valorMin, Double valorMax) {
        return jpaImovelRepository.buscarPorFaixaDeValor(valorMin, valorMax);
    }

    @Override
    public List<Imovel> listarPorFiltros(
            Double valorMin,
            Double valorMax,
            String cidade
    ) {
        Specification<Imovel> spec = Specification.unrestricted();

        if (cidade != null) {
            spec = spec.and(ImovelSpecification.cidadeIgual(cidade));
        }

        if (valorMin != null) {
            spec = spec.and(ImovelSpecification.valorMaiorOuIgual(valorMin));
        }

        if (valorMax != null) {
            spec = spec.and(ImovelSpecification.valorMenorOuIgual(valorMax));
        }

        return jpaImovelRepository.findAll(spec);
    }

    @Override
    public Imovel buscarPorId(Long id) {
        return jpaImovelRepository.findById(id).orElse(null);
    }

    @Override
    public void deletarPorId(Long id) {
        jpaImovelRepository.deleteById(id);
    }

    @Override
    public Imovel atualizar(Imovel imovel) {
        return jpaImovelRepository.save(imovel);
    }
}