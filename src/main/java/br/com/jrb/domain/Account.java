package br.com.jrb.domain;

import br.com.jrb.exception.DomainException;

public class Account {
    private double saldo;

    public Account(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new DomainException("Valor inválido para saque.");
        }
        if (valor > saldo) {
            throw new DomainException("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    public void registrarOperacao(String tipo, double valor) {
        System.out.println(tipo + " " + valor + " registrado");
    }
}
