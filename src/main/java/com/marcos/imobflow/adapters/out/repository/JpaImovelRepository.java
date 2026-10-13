package com.marcos.imobflow.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImovelRepository
        extends JpaRepository<ImovelEntity, Long>, JpaSpecificationExecutor<ImovelEntity> {
}
