package br.com.fiap.techchallenge.lanchonete.entities.exception;

import java.util.Map;

public class ClienteException extends RuntimeException {

    private final String cpf;

    public ClienteException(String message) {
        super(message);
        this.cpf = null;
    }

    public ClienteException(String message, String cpf) {
        super(message);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
