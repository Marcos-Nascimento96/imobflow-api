package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.ImovelResponse;
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
        ImovelResponse response =
                useCase.executar(1L, request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Apartamento novo", response.titulo());
        assertEquals("Descrição atualizada", response.descricao());
        assertEquals("São Bernardo do Campo", response.cidade());
        assertEquals("Centro", response.bairro());
        assertEquals("Rua nova, 200", response.endereco());
        assertEquals(450000.0, response.valor());
        assertEquals("VENDA", response.tipoNegociacao());
        assertEquals("APARTAMENTO", response.tipoImovel());
        assertEquals(3, response.quartos());
        assertEquals(2, response.banheiros());
        assertEquals(2, response.vagasGaragem());
        assertEquals(75.0, response.areaM2());
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
