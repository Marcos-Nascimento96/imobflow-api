package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.exception.ImovelNotFoundException;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeletarImovelPorIdUseCaseTest {

    @Test
    void deveDeletarImovelComSucesso() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);

        DeletarImovelPorIdUseCase useCase =
                new DeletarImovelPorIdUseCase(imovelRepository);

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

        when(imovelRepository.buscarPorId(1L))
                .thenReturn(imovel);

        // Act
        useCase.executar(1L);

        // Assert
        verify(imovelRepository, times(1))
                .deletarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoImovelNaoForEncontrado() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);

        DeletarImovelPorIdUseCase useCase =
                new DeletarImovelPorIdUseCase(imovelRepository);

        when(imovelRepository.buscarPorId(99L))
                .thenReturn(null);

        // Act / Assert
        assertThrows(
                ImovelNotFoundException.class,
                () -> useCase.executar(99L)
        );

        verify(imovelRepository, never())
                .deletarPorId(99L);
    }
}
