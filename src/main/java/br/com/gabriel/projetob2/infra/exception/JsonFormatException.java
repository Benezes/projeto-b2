package br.com.gabriel.projetob2.infra.exception;

public class JsonFormatException extends RuntimeException {
    public JsonFormatException(String msg) {
        super(msg);
    }

    public JsonFormatException() {
    }
}
