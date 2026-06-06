package com.marcos.imobflow.domain.repository;

import com.marcos.imobflow.domain.model.Imovel;

import java.util.List;

public interface ImovelRepository {

    Imovel salvar(Imovel imovel);

    List<Imovel> listar();

    Imovel buscarPorId(Long id);

    void deletarPorId(Long id);

    Imovel atualizar(Imovel imovel);


}