package br.com.jrb;

import java.util.Scanner;

import br.com.jrb.service.ContaService;
import br.com.jrb.exception.DomainException;

public class Main {
    public static void main(String[] args) {
        ContaService service = new ContaService();
        Scanner sc = new Scanner(System.in);

        // cria uma conta de teste
        String cpf = "111.111.111-11";
        service.adicionarConta(cpf, 500.00);

        System.out.println("Saldo inicial: " + service.getSaldo(cpf));

        System.out.print("Digite o valor para saque: ");
        double valor = sc.nextDouble();

        try {
            service.sacar(cpf, valor);
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo final: " + service.getSaldo(cpf));
        } catch (DomainException e) {
            System.out.println("Erro ao sacar: " + e.getMessage());
        }

        sc.close();
    }
}
