package br.com.jrb.service;

import br.com.jrb.domain.Account;
import br.com.jrb.exception.DomainException;
import java.util.HashMap;
import java.util.Map;

public class ContaService {
    private Map<String, Account> contas = new HashMap<>();

    public void adicionarConta(String cpf, double saldo) {
        contas.put(cpf, new Account(saldo));
    }

    public void sacar(String cpf, double valor) {
        Account c = contas.get(cpf);
        if (c == null) throw new DomainException("Conta não encontrada.");
        c.sacar(valor);
        c.registrarOperacao("Saque", -valor);
    }

    public double getSaldo(String cpf) {
        Account c = contas.get(cpf);
        if (c == null) throw new DomainException("Conta não encontrada.");
        return c.getSaldo();
    }
}
