package org.example;

import java.util.Scanner;

// Faça um programa que receba 5 salários brutos de funcionários, sabendo-se que são descontados Imposto de Renda e INSS,
// calcule e mostre o total do salário líquido no referido mês.
//
// Obs.: Salário Bruto - Descontos = Salário Líquido.
//
// A saída do programa deve fornecer as seguintes informações:
//
// Salário bruto.
// Quanto pagou ao INSS.
// Quanto pagou de Imposto de Renda.
// Salário líquido.
// Calcule os descontos e o salário líquido com base nas tabelas abaixo:
//
// Salário	% Desconto INSS
// até 1.212,00	7,5%
// de 1212,01 até 2.427,35	9%
// de 2.427,36 até 3.641,03	12%
// de 3.641,04 até 7.087,22	14%
// Salário	% Desconto Imposto de Renda
// até 1.903,98	0%
// de 1.903,99 até 2.826,65	7,5%
// de 2.826,66 até 3.751,05	15%
// de 3.751,06 até 4.664,68	22,50%
// Acima de 4.664,68	27,50%

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantidadeSalarios = 5;
        boolean runProgram = true;

        while (runProgram) {
            for (int i = 0; i < quantidadeSalarios; i++) {
                boolean salarioValidado = false;

                while (!salarioValidado) {
                    System.out.println("_________________________________________");
                    System.out.println("Digite o valor do " + (i+1) + "º salario.");
                    String entrada = scanner.nextLine();

                    try {
                        double salario = Double.parseDouble(entrada);
                        boolean salarioNegativo = salario < 0;

                        if (salarioNegativo) {
                            System.out.println("Por favor, não digite um salário abaixo de 0.");
                        } else {
                            System.out.println("O salário bruto digitado foi de: R$ " + salario + ".");

                            double percentualINSS = calcularINSS(salario);
                            double pagoINSS = (percentualINSS/100) * salario;
                            System.out.println("O percentual a se descontar de inss no salario é de:" + percentualINSS + "%");
                            System.out.println("O valor pago de INSS foi: R$ " + formatarNumero(pagoINSS) + ".");

                            double percentualIR = calcularIR(salario);
                            double pagoIR = (percentualIR/100) * salario;
                            System.out.println("O percentual a se descontar de IR no salario é de:" + percentualIR + "%");
                            System.out.println("O valor pago de IR foi: R$ " + formatarNumero(pagoIR) + ".");

                            double salarioLiquido = salario - pagoINSS - pagoIR;
                            System.out.println("O salario líquido do " + (i+1) + "º funcionário foi de: R$" +
                                    formatarNumero(salarioLiquido) + ".");

                            salarioValidado = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor digite um número válido.");
                    }
                }
            }

            runProgram = false;
            scanner.close();
        }
    }

    public static double calcularINSS(double salario) {
        double descontoINSS = 0;

        if (salario <= 1212.00) {
            descontoINSS = 7.5;
        } else if(salario <= 2427.35) {
            descontoINSS = 9;
        } else if (salario <= 3641.03) {
            descontoINSS = 12;
        } else {
            descontoINSS = 14;
        }

        return descontoINSS;
    }

    public static double calcularIR(double salario) {
        double descontoIR = 0;

        if (salario <= 1903.98) {
            descontoIR = 0;
        } else if (salario <= 2826.65) {
            descontoIR = 7.5;
        } else if (salario <= 3751.05) {
            descontoIR = 15;
        } else if (salario <= 4664.68) {
            descontoIR = 22.5;
        } else {
            descontoIR = 27.5;
        }

        return descontoIR;
    }

    public static String formatarNumero(double x) {
        return String.format("%.2f", x);
    }
}