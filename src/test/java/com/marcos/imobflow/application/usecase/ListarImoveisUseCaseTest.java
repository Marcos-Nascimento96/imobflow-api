package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
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
        List<Imovel> response = useCase.executar(null, null);

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

        when(imovelRepository.listarPorFaixaDeValor(valorMin, valorMax))
                .thenReturn(imoveis);

        // Act
        List<Imovel> response = useCase.executar(valorMin, valorMax);

        // Assert
        assertEquals(imoveis, response);

        verify(imovelRepository)
                .listarPorFaixaDeValor(valorMin, valorMax);
    }

    @Test
    void deveListarImoveisSomenteComValorMinimo() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Double valorMin = 400000.0;

        when(imovelRepository.listarPorFaixaDeValor(valorMin, null))
                .thenReturn(List.of());

        // Act
        useCase.executar(valorMin, null);

        // Assert
        verify(imovelRepository)
                .listarPorFaixaDeValor(valorMin, null);
    }

    @Test
    void deveListarImoveisSomenteComValorMaximo() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        ListarImoveisUseCase useCase =
                new ListarImoveisUseCase(imovelRepository);

        Double valorMax = 600000.0;

        when(imovelRepository.listarPorFaixaDeValor(null, valorMax))
                .thenReturn(List.of());

        // Act
        useCase.executar(null, valorMax);

        // Assert
        verify(imovelRepository)
                .listarPorFaixaDeValor(null, valorMax);
    }
}
