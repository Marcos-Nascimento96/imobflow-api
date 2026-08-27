package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}
