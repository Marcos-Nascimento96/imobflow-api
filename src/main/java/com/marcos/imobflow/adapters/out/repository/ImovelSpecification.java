package com.marcos.imobflow.adapters.out.repository;

import org.springframework.data.jpa.domain.Specification;

public final class ImovelSpecification {

    private ImovelSpecification() {
    }

    public static Specification<ImovelEntity> cidadeIgual(String cidade) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cidade"), cidade);
    }

    public static Specification<ImovelEntity> valorMaiorOuIgual(Double valorMin) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("valor"), valorMin);
    }

    public static Specification<ImovelEntity> valorMenorOuIgual(Double valorMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("valor"), valorMax);
    }
}
