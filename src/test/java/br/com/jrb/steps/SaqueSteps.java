package br.com.jrb.steps;

import br.com.jrb.service.ContaService;
import br.com.jrb.exception.DomainException;
import io.cucumber.java.pt.*;
import static org.junit.Assert.*;

public class SaqueSteps {

    private ContaService service;
    private Exception erro;
    private double saldoAtual;

    @Dado("que a conta {string} tem saldo {double}")
    public void criarConta(String cpf, double saldo) {
        service = new ContaService();
        service.adicionarConta(cpf, saldo);
        saldoAtual = saldo;
    }

    @Quando("o usuário solicita saque de {double} para o CPF {string}")
    public void solicitarSaque(double valor, String cpf) {
        erro = null;
        try {
            service.sacar(cpf, valor);
            saldoAtual = service.getSaldo(cpf);
        } catch (DomainException e) {
            erro = e;
        }
    }

    @Então("o saldo restante deve ser {double}")
    public void validarSaldo(double esperado) {
        assertNull(erro);
        assertEquals(esperado, saldoAtual, 0.001);
    }

    @Então("o sistema deve lançar erro {string}")
    public void validarErro(String esperado) {
        assertNotNull(erro);
        assertEquals(esperado, erro.getMessage());
    }
}
