# 📘 Exercício BDD – Funcionalidade de Saque (JRB-SO)

## 1 – Como ficou sua história?

Após a conversa entre Cliente e Product Owner:

> **Como cliente do banco JRB**, quero sacar dinheiro da minha conta, inserindo a quantidade desejada e confirmando, para poder ter o valor em mãos, desde que o valor seja válido e haja saldo suficiente.

### Critérios de Aceitação (BDD)
- Debitar valor quando o saque é válido e há saldo suficiente
- Zerar saldo quando o saque é igual ao saldo disponível
- Recusar saque maior que o saldo com mensagem `"Saldo insuficiente."`
- Recusar valores `<= 0` com mensagem `"Valor inválido para saque."`

---

## 2 – Como ficaram seus cenários? (Gherkin)

```gherkin
# language: pt

Funcionalidade: Saque de dinheiro

  Cenário: Saque com saldo suficiente
    Dado que a conta "111.111.111-11" tem saldo 500.00
    Quando o usuário solicita saque de 200.00 para o CPF "111.111.111-11"
    Então o saldo restante deve ser 300.00

  Cenário: Saque igual ao saldo disponível
    Dado que a conta "222.222.222-22" tem saldo 400.00
    Quando o usuário solicita saque de 400.00 para o CPF "222.222.222-22"
    Então o saldo restante deve ser 0.00

  Cenário: Saque com valor superior ao saldo
    Dado que a conta "333.333.333-33" tem saldo 100.00
    Quando o usuário solicita saque de 150.00 para o CPF "333.333.333-33"
    Então o sistema deve lançar erro "Saldo insuficiente."

  Cenário: Saque com valor inválido
    Dado que a conta "444.444.444-44" tem saldo 300.00
    Quando o usuário solicita saque de 0.00 para o CPF "444.444.444-44"
    Então o sistema deve lançar erro "Valor inválido para saque."
```

---

## 3 – Como foi implementado o teste de 1 cenário?

Usando Cucumber + JUnit 4, com `assert` para validação real no método de Steps:

```java
package br.com.jrb.steps;

import br.com.jrb.service.ContaService;
import br.com.jrb.exception.DomainException;
import io.cucumber.java.pt.*;
import static org.junit.Assert.*;

public class SaqueSteps {

    private ContaService service;
    private Exception erro;
    private double saldoAtual;
    private String cpf;

    @Dado("que a conta {string} tem saldo {double}")
    public void criarConta(String cpf, double saldo) {
        this.cpf = cpf;
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
```

---

# ▶️ Como rodar o projeto e os testes

### No terminal (na raiz do projeto):

```sh

mvn clean test
```

O Maven irá recompilar com UTF‑8, executar o Runner JUnit que aciona o Cucumber e rodar todos os cenários `.feature`.

### Saída esperada:

```
Tests run: 4, Failures: 0, Errors: 0
BUILD SUCCESS
```


