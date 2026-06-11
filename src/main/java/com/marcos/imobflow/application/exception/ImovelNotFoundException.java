package com.marcos.imobflow.application.exception;

public class ImovelNotFoundException extends RuntimeException {

    public ImovelNotFoundException() {
        super("Imóvel não encontrado");
    }
}