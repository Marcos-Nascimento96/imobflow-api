package com.marcos.imobflow.application.usecase;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.ImovelResponse;
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
        ImovelResponse response = useCase.executar(request);

        // Assert
        assertEquals(1L, response.id());
        assertEquals("Apartamento no Centro", response.titulo());
        assertEquals("Apartamento bem localizado", response.descricao());
        assertEquals("São Bernardo do Campo", response.cidade());
        assertEquals("Centro", response.bairro());
        assertEquals("Rua das Flores, 100", response.endereco());
        assertEquals(450000.0, response.valor());
        assertEquals("VENDA", response.tipoNegociacao());
        assertEquals("APARTAMENTO", response.tipoImovel());
        assertEquals(2, response.quartos());
        assertEquals(1, response.banheiros());
        assertEquals(1, response.vagasGaragem());
        assertEquals(65.5, response.areaM2());
    }
}
