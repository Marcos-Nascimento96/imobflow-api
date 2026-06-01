package com.marcos.imobflow.adapters.in.controller;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.application.usecase.CadastrarImovelUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final CadastrarImovelUseCase cadastrarImovelUseCase;

    public ImovelController(CadastrarImovelUseCase cadastrarImovelUseCase) {
        this.cadastrarImovelUseCase = cadastrarImovelUseCase;
    }

    @PostMapping
    public CreateImovelResponse cadastrar(@RequestBody CreateImovelRequest request) {
        return cadastrarImovelUseCase.executar(request);
    }
}
