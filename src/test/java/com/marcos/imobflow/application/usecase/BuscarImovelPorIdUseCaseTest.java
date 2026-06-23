package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscarImovelPorIdUseCaseTest {

    @Test
    void deveBuscarImovelPorIdComSucesso() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        BuscarImovelPorIdUseCase useCase = new BuscarImovelPorIdUseCase(imovelRepository);

        Imovel imovel = new Imovel(
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

        when(imovelRepository.buscarPorId(1L)).thenReturn(imovel);

        // Act
        Imovel response = useCase.executar(1L);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Apartamento no Centro", response.getTitulo());
        assertEquals("Apartamento bem localizado", response.getDescricao());
        assertEquals("São Bernardo do Campo", response.getCidade());
    }
}