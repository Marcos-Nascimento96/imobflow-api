package com.marcos.imobflow.adapters.in.controller;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.application.usecase.CadastrarImovelUseCase;
import com.marcos.imobflow.application.usecase.ListarImoveisUseCase;
import com.marcos.imobflow.domain.model.Imovel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final CadastrarImovelUseCase cadastrarImovelUseCase;
    private final ListarImoveisUseCase listarImoveisUseCase;

    public ImovelController(
            CadastrarImovelUseCase cadastrarImovelUseCase,
            ListarImoveisUseCase listarImoveisUseCase
    ) {
        this.cadastrarImovelUseCase = cadastrarImovelUseCase;
        this.listarImoveisUseCase = listarImoveisUseCase;
    }

    @PostMapping
    public CreateImovelResponse cadastrar(@RequestBody CreateImovelRequest request) {
        return cadastrarImovelUseCase.executar(request);
    }

    @GetMapping
    public List<Imovel> listar() {
        return listarImoveisUseCase.executar();
    }
}
