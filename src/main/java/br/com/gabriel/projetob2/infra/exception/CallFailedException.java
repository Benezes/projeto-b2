package br.com.gabriel.projetob2.infra.exception;

public class CallFailedException extends RuntimeException {
    public CallFailedException(String msg) {
        super(msg);
    }

    public CallFailedException() {
        super();
    }
}
