package com.marcos.imobflow.infrastructure.repository;

import com.marcos.imobflow.domain.model.Imovel;
import org.springframework.data.jpa.domain.Specification;

public class ImovelSpecification {

    public static Specification<Imovel> cidadeIgual(String cidade) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("cidade"),
                        cidade
                );
    }

    public static Specification<Imovel> valorMaiorOuIgual(Double valorMin) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("valor"),
                        valorMin
                );
    }
    public static Specification<Imovel> valorMenorOuIgual(Double valorMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("valor"),
                        valorMax
                );
    }
}