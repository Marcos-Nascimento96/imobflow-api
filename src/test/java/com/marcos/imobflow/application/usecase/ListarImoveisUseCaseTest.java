package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import com.marcos.imobflow.application.dto.ImovelFiltro;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ListarImoveisUseCaseTest {

    @Test
    void deveListarImoveisComSucesso() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);

        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Imovel imovel1 = new Imovel(
                1L,
                "Apartamento no Centro",
                "Apartamento bem localizado",
                "São Bernardo do Campo",
                "Centro",
                "Rua das Flores, 100",
                450000.0,
                "VENDA",
                "APARTAMENTO",
                2,
                1,
                1,
                65.5
        );

        Imovel imovel2 = new Imovel(
                2L,
                "Casa com garagem",
                "Casa ampla em bairro residencial",
                "Santo André",
                "Jardim",
                "Rua das Palmeiras, 200",
                650000.0,
                "VENDA",
                "CASA",
                3,
                2,
                2,
                120.0
        );

        when(imovelRepository.listar())
                .thenReturn(List.of(imovel1, imovel2));

        // Act
        ImovelFiltro filtro = new ImovelFiltro(null, null, null);

        List<Imovel> response = useCase.executar(filtro);

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size());

        assertEquals(1L, response.get(0).getId());
        assertEquals("Apartamento no Centro", response.get(0).getTitulo());

        assertEquals(2L, response.get(1).getId());
        assertEquals("Casa com garagem", response.get(1).getTitulo());
    }
    @Test
    void deveListarImoveisPorFaixaDeValor() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Double valorMin = 400000.0;
        Double valorMax = 600000.0;

        List<Imovel> imoveis = List.of(
                new Imovel()
        );

        when(imovelRepository.listarPorFiltros(valorMin, valorMax, null))
                .thenReturn(imoveis);

        // Act
        ImovelFiltro filtro = new ImovelFiltro(valorMin, valorMax, null);
        List<Imovel> response = useCase.executar(filtro);

        // Assert
        assertEquals(imoveis, response);

        verify(imovelRepository)
                .listarPorFiltros(valorMin, valorMax, null);
    }

    @Test
    void deveListarImoveisSomenteComValorMinimo() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Double valorMin = 400000.0;

        when(imovelRepository.listarPorFiltros(valorMin, null, null))
                .thenReturn(List.of());

        // Act
        ImovelFiltro filtro = new ImovelFiltro(valorMin, null, null);
        useCase.executar(filtro);

        // Assert
        verify(imovelRepository)
                .listarPorFiltros(valorMin, null, null);
    }

    @Test
    void deveListarImoveisSomenteComValorMaximo() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Double valorMax = 600000.0;

        when(imovelRepository.listarPorFiltros(null, valorMax, null))
                .thenReturn(List.of());

        // Act
        ImovelFiltro filtro = new ImovelFiltro(null, valorMax, null);
        useCase.executar(filtro);

        // Assert
        verify(imovelRepository)
                .listarPorFiltros(null, valorMax, null);
    }

    @Test
    void deveListarImoveisComCidadeEFaixaDeValor() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Double valorMin = 400000.0;
        Double valorMax = 600000.0;
        String cidade = "SBC";

        Imovel imovel = new Imovel();

        List<Imovel> imoveis = List.of(imovel);

        when(imovelRepository.listarPorFiltros(valorMin, valorMax, cidade))
                .thenReturn(imoveis);

        ImovelFiltro filtro =
                new ImovelFiltro(valorMin, valorMax, cidade);

        // Act
        List<Imovel> response = useCase.executar(filtro);

        // Assert
        assertEquals(imoveis, response);

        verify(imovelRepository)
                .listarPorFiltros(valorMin, valorMax, cidade);
    }
}
