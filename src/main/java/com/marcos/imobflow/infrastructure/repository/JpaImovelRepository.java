package com.marcos.imobflow.infrastructure.repository;

import com.marcos.imobflow.domain.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImovelRepository extends JpaRepository<Imovel, Long> {
}