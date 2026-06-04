package com.marcos.imobflow.adapters.in.controller;

import com.marcos.imobflow.application.dto.CreateImovelRequest;
import com.marcos.imobflow.application.dto.CreateImovelResponse;
import com.marcos.imobflow.application.usecase.CadastrarImovelUseCase;
import com.marcos.imobflow.application.usecase.ListarImoveisUseCase;
import com.marcos.imobflow.domain.model.Imovel;
import com.marcos.imobflow.application.usecase.BuscarImovelPorIdUseCase;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final BuscarImovelPorIdUseCase buscarImovelPorIdUseCase;

    public ImovelController(
            CadastrarImovelUseCase cadastrarImovelUseCase,
            ListarImoveisUseCase listarImoveisUseCase,
            BuscarImovelPorIdUseCase buscarImovelPorIdUseCase
    ) {
        this.cadastrarImovelUseCase = cadastrarImovelUseCase;
        this.listarImoveisUseCase = listarImoveisUseCase;
        this.buscarImovelPorIdUseCase = buscarImovelPorIdUseCase;
    }

    @PostMapping
    public CreateImovelResponse cadastrar(@RequestBody CreateImovelRequest request) {
        return cadastrarImovelUseCase.executar(request);
    }

    @GetMapping
    public List<Imovel> listar() {
        return listarImoveisUseCase.executar();
    }

    @GetMapping("/{id}")
    public Imovel buscarPorId(@PathVariable Long id) {
        return buscarImovelPorIdUseCase.executar(id);
    }
}
