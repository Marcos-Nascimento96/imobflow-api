package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.domain.repository.ImovelRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CadastrarImovelUseCaseTest {

    @Test
    void deveCadastrarImovelComSucesso() {

        // Arrange
        ImovelRepository imovelRepository = mock(ImovelRepository.class);
        CadastrarImovelUseCase useCase = new CadastrarImovelUseCase(imovelRepository);

        CreateImovelRequest request = mock(CreateImovelRequest.class);

        when(request.getTitulo()).thenReturn("Apartamento no Centro");
        when(request.getDescricao()).thenReturn("Apartamento bem localizado");
        when(request.getCidade()).thenReturn("São Bernardo do Campo");
        when(request.getBairro()).thenReturn("Centro");
        when(request.getEndereco()).thenReturn("Rua das Flores, 100");
        when(request.getValor()).thenReturn(450000.0);
        when(request.getTipoNegociacao()).thenReturn("VENDA");
        when(request.getTipoImovel()).thenReturn("APARTAMENTO");
        when(request.getQuartos()).thenReturn(2);
        when(request.getBanheiros()).thenReturn(1);
        when(request.getVagasGaragem()).thenReturn(1);
        when(request.getAreaM2()).thenReturn(65.5);

        Imovel imovelSalvo = new Imovel(
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

        when(imovelRepository.salvar(any(Imovel.class))).thenReturn(imovelSalvo);

        // Act
        CreateImovelResponse response = useCase.executar(request);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Apartamento no Centro", response.getTitulo());
        assertEquals("Apartamento bem localizado", response.getDescricao());
        assertEquals("São Bernardo do Campo", response.getCidade());
        assertEquals("Centro", response.getBairro());
        assertEquals("Rua das Flores, 100", response.getEndereco());
        assertEquals(450000.0, response.getValor());
        assertEquals("VENDA", response.getTipoNegociacao());
        assertEquals("APARTAMENTO", response.getTipoImovel());
        assertEquals(2, response.getQuartos());
        assertEquals(1, response.getBanheiros());
        assertEquals(1, response.getVagasGaragem());
        assertEquals(65.5, response.getAreaM2());
    }
}