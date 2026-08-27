package com.marcos.imobflow.infrastructure.repository;

import com.marcos.imobflow.domain.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaImovelRepository extends JpaRepository<Imovel, Long> {

    @Query("""
            SELECT i FROM Imovel i
            WHERE (:valorMin IS NULL OR i.valor >= :valorMin)
              AND (:valorMax IS NULL OR i.valor <= :valorMax)
            """)
    List<Imovel> buscarPorFaixaDeValor(
            @Param("valorMin") Double valorMin,
            @Param("valorMax") Double valorMax
    );
}