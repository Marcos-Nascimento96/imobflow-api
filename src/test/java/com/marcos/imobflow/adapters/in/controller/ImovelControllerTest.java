package com.marcos.imobflow.adapters.in.controller;

import com.marcos.imobflow.application.dto.ImovelResponse;
import com.marcos.imobflow.application.usecase.AtualizarImovelPorIdUseCase;
import com.marcos.imobflow.application.usecase.BuscarImovelPorIdUseCase;
import com.marcos.imobflow.application.usecase.CadastrarImovelUseCase;
import com.marcos.imobflow.application.usecase.DeletarImovelPorIdUseCase;
import com.marcos.imobflow.application.usecase.ListarImoveisUseCase;
import com.marcos.imobflow.config.SecurityConfig;
import com.marcos.imobflow.application.exception.ImovelNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @DisplayName("POST /imoveis - Deve cadastrar imóvel e retornar ImovelResponse com sucesso")
    void deveCadastrarImovelComSucesso() throws Exception {
        ImovelResponse response = imovelResponsePadrao(1L);

        when(cadastrarImovelUseCase.executar(any())).thenReturn(response);

        assertImovelJson(mockMvc.perform(post("/imoveis")
                        .contentType("application/json")
                        .content(jsonRequestPadrao()))
                .andExpect(status().isOk()), "$", 1L);
    }

    @Test
    @DisplayName("POST /imoveis - Deve retornar 400 quando dados são inválidos")
    void deveRetornarBadRequestQuandoDadosForemInvalidos() throws Exception {
        String jsonInvalido = """
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

        mockMvc.perform(post("/imoveis")
                        .contentType("application/json")
                        .content(jsonInvalido))
                .andExpect(status().isBadRequest());

        verify(cadastrarImovelUseCase, never()).executar(any());
    }

    @Test
    @DisplayName("GET /imoveis/{id} - Deve buscar imóvel por ID com sucesso")
    void deveBuscarImovelPorIdComSucesso() throws Exception {
        ImovelResponse response = imovelResponsePadrao(1L);

        when(buscarImovelPorIdUseCase.executar(1L)).thenReturn(response);

        assertImovelJson(
                mockMvc.perform(get("/imoveis/1")).andExpect(status().isOk()),
                "$",
                1L
        );
    }

    @Test
    @DisplayName("GET /imoveis/{id} - Deve retornar 404 quando imóvel não existir")
    void deveRetornarNotFoundQuandoImovelNaoExistir() throws Exception {
        when(buscarImovelPorIdUseCase.executar(999L)).thenThrow(new ImovelNotFoundException());

        mockMvc.perform(get("/imoveis/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Imóvel não encontrado"));
    }

    @Test
    @DisplayName("GET /imoveis - Deve listar imóveis e retornar List de ImovelResponse")
    void deveListarImoveisComSucesso() throws Exception {
        List<ImovelResponse> lista = List.of(imovelResponsePadrao(1L), imovelResponsePadrao(2L));

        when(listarImoveisUseCase.executar(any())).thenReturn(lista);

        ResultActions result = mockMvc.perform(get("/imoveis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        assertImovelJson(result, "$[0]", 1L);
        assertImovelJson(result, "$[1]", 2L);
    }

    @Test
    @DisplayName("PUT /imoveis/{id} - Deve atualizar imóvel e retornar ImovelResponse")
    void deveAtualizarImovelComSucesso() throws Exception {
        ImovelResponse response = imovelResponsePadrao(1L);

        when(atualizarImovelPorIdUseCase.executar(eq(1L), any())).thenReturn(response);

        assertImovelJson(mockMvc.perform(put("/imoveis/1")
                        .contentType("application/json")
                        .content(jsonRequestPadrao()))
                .andExpect(status().isOk()), "$", 1L);
    }

    @Test
    @DisplayName("DELETE /imoveis/{id} - Deve deletar imóvel com sucesso")
    void deveDeletarImovelComSucesso() throws Exception {
        mockMvc.perform(delete("/imoveis/1"))
                .andExpect(status().isOk());

        verify(deletarImovelPorIdUseCase).executar(1L);
    }

    private ImovelResponse imovelResponsePadrao(Long id) {
        return new ImovelResponse(
                id,
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
    }

    private void assertImovelJson(ResultActions result, String path, Long id) throws Exception {
        result.andExpect(jsonPath(path + ".id").value(id))
                .andExpect(jsonPath(path + ".titulo").value("Apartamento no Centro"))
                .andExpect(jsonPath(path + ".descricao").value("Apartamento bem localizado"))
                .andExpect(jsonPath(path + ".cidade").value("São Bernardo do Campo"))
                .andExpect(jsonPath(path + ".bairro").value("Centro"))
                .andExpect(jsonPath(path + ".endereco").value("Rua das Flores, 100"))
                .andExpect(jsonPath(path + ".valor").value(450000.0))
                .andExpect(jsonPath(path + ".tipoNegociacao").value("VENDA"))
                .andExpect(jsonPath(path + ".tipoImovel").value("APARTAMENTO"))
                .andExpect(jsonPath(path + ".quartos").value(2))
                .andExpect(jsonPath(path + ".banheiros").value(1))
                .andExpect(jsonPath(path + ".vagasGaragem").value(1))
                .andExpect(jsonPath(path + ".areaM2").value(65.5));
    }

    private String jsonRequestPadrao() {
        return """
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
    }
}
