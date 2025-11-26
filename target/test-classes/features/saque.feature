Feature: Saque de dinheiro
  Como cliente do banco JRB
  Eu quero sacar dinheiro da minha conta
  Para ter o valor em mãos

  Scenario: Saque com saldo suficiente
    Given que a conta "111.111.111-11" tem saldo 500.00
    When o usuário solicita saque de 200.00 para o CPF "111.111.111-11"
    Then o saldo restante deve ser 300.00

  Scenario: Saque igual ao saldo disponível
    Given que a conta "222.222.222-22" tem saldo 400.00
    When o usuário solicita saque de 400.00 para o CPF "222.222.222-22"
    Then o saldo restante deve ser 0.00

  Scenario: Saque com valor superior ao saldo
    Given que a conta "333.333.333-33" tem saldo 100.00
    When o usuário solicita saque de 150.00 para o CPF "333.333.333-33"
    Then o sistema deve lançar erro "Saldo insuficiente."

  Scenario: Saque com valor inválido
    Given que a conta "444.444.444-44" tem saldo 300.00
    When o usuário solicita saque de 0.00 para o CPF "444.444.444-44"
    Then o sistema deve lançar erro "Valor inválido para saque."
