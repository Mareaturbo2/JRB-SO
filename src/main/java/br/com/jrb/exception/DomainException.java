package br.com.jrb.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
