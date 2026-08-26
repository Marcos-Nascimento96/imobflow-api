package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.application.exception.ImovelNotFoundException;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AtualizarImovelPorIdUseCaseTest {

    @Test
    void deveAtualizarImovelComSucesso() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);

        AtualizarImovelPorIdUseCase useCase =
                new AtualizarImovelPorIdUseCase(imovelRepository);

        Imovel imovelExistente = new Imovel(
                1L,
                "Apartamento antigo",
                "Descrição antiga",
                "São Bernardo do Campo",
                "Centro",
                "Rua antiga, 100",
                400000.0,
                "VENDA",
                "APARTAMENTO",
                2,
                1,
                1,
                60.0
        );

        CreateImovelRequest request = new CreateImovelRequest(
                "Apartamento novo",
                "Descrição atualizada",
                "São Bernardo do Campo",
                "Centro",
                "Rua nova, 200",
                450000.0,
                "VENDA",
                "APARTAMENTO",
                3,
                2,
                2,
                75.0
        );

        Imovel imovelAtualizado = new Imovel(
                1L,
                "Apartamento novo",
                "Descrição atualizada",
                "São Bernardo do Campo",
                "Centro",
                "Rua nova, 200",
                450000.0,
                "VENDA",
                "APARTAMENTO",
                3,
                2,
                2,
                75.0
        );

        when(imovelRepository.buscarPorId(1L))
                .thenReturn(imovelExistente);

        when(imovelRepository.atualizar(any(Imovel.class)))
                .thenReturn(imovelAtualizado);

        // Act
        CreateImovelResponse response =
                useCase.executar(1L, request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Apartamento novo", response.getTitulo());
        assertEquals("Descrição atualizada", response.getDescricao());
        assertEquals("São Bernardo do Campo", response.getCidade());
        assertEquals("Centro", response.getBairro());
        assertEquals("Rua nova, 200", response.getEndereco());
        assertEquals(450000.0, response.getValor());
        assertEquals("VENDA", response.getTipoNegociacao());
        assertEquals("APARTAMENTO", response.getTipoImovel());
        assertEquals(3, response.getQuartos());
        assertEquals(2, response.getBanheiros());
        assertEquals(2, response.getVagasGaragem());
        assertEquals(75.0, response.getAreaM2());
    }

    @Test
    void deveLancarExcecaoQuandoImovelNaoForEncontrado() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);

        AtualizarImovelPorIdUseCase useCase =
                new AtualizarImovelPorIdUseCase(imovelRepository);

        CreateImovelRequest request = new CreateImovelRequest(
                "Apartamento novo",
                "Descrição atualizada",
                "São Bernardo do Campo",
                "Centro",
                "Rua nova, 200",
                450000.0,
                "VENDA",
                "APARTAMENTO",
                3,
                2,
                2,
                75.0
        );

        when(imovelRepository.buscarPorId(99L))
                .thenReturn(null);

        // Act / Assert
        ImovelNotFoundException exception = assertThrows(
                ImovelNotFoundException.class,
                () -> useCase.executar(99L, request)
        );

        assertEquals(
                "Imóvel não encontrado",
                exception.getMessage()
        );
    }
}