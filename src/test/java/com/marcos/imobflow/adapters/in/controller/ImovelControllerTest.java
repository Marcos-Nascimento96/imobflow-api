package com.marcos.imobflow.adapters.in.controller;

import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.application.usecase.AtualizarImovelPorIdUseCase;
import com.marcos.imobflow.application.usecase.BuscarImovelPorIdUseCase;
import com.marcos.imobflow.application.usecase.CadastrarImovelUseCase;
import com.marcos.imobflow.application.usecase.DeletarImovelPorIdUseCase;
import com.marcos.imobflow.application.usecase.ListarImoveisUseCase;
import com.marcos.imobflow.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.application.exception.ImovelNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ImovelController.class)
@Import(SecurityConfig.class)
class ImovelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CadastrarImovelUseCase cadastrarImovelUseCase;

    @MockitoBean
    private ListarImoveisUseCase listarImoveisUseCase;

    @MockitoBean
    private BuscarImovelPorIdUseCase buscarImovelPorIdUseCase;

    @MockitoBean
    private DeletarImovelPorIdUseCase deletarImovelPorIdUseCase;

    @MockitoBean
    private AtualizarImovelPorIdUseCase atualizarImovelPorIdUseCase;

    @Test
    void deveCadastrarImovelComSucesso() throws Exception {

        // Arrange
        CreateImovelResponse response = new CreateImovelResponse(
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

        when(cadastrarImovelUseCase.executar(any()))
                .thenReturn(response);

        String json = """
                {
                  "titulo": "Apartamento no Centro",
                  "descricao": "Apartamento bem localizado",
                  "cidade": "São Bernardo do Campo",
                  "bairro": "Centro",
                  "endereco": "Rua das Flores, 100",
                  "valor": 450000.0,
                  "tipoNegociacao": "VENDA",
                  "tipoImovel": "APARTAMENTO",
                  "quartos": 2,
                  "banheiros": 1,
                  "vagasGaragem": 1,
                  "areaM2": 65.5
                }
                """;

        // Act / Assert
        mockMvc.perform(post("/imoveis")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Apartamento no Centro"))
                .andExpect(jsonPath("$.valor").value(450000.0));
    }

    @Test
    void deveRetornarBadRequestQuandoDadosForemInvalidos() throws Exception {

        // Arrange
        String json = """
                {
                  "titulo": "",
                  "descricao": "",
                  "cidade": "",
                  "bairro": "",
                  "endereco": "",
                  "valor": -1000.0,
                  "tipoNegociacao": "",
                  "tipoImovel": "",
                  "quartos": -1,
                  "banheiros": -1,
                  "vagasGaragem": -1,
                  "areaM2": -10.0
                }
                """;

        // Act / Assert
        mockMvc.perform(post("/imoveis")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());

        verify(cadastrarImovelUseCase, never())
                .executar(any());
    }

    @Test
    void deveBuscarImovelPorIdComSucesso() throws Exception {

        // Arrange
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

        when(buscarImovelPorIdUseCase.executar(1L))
                .thenReturn(imovel);

        // Act / Assert
        mockMvc.perform(get("/imoveis/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Apartamento no Centro"))
                .andExpect(jsonPath("$.valor").value(450000.0));
    }

    @Test
    void deveRetornarNotFoundQuandoImovelNaoExistir() throws Exception {

        // Arrange
        when(buscarImovelPorIdUseCase.executar(999L))
                .thenThrow(new ImovelNotFoundException());

        // Act / Assert
        mockMvc.perform(get("/imoveis/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Imóvel não encontrado"));
    }
}

