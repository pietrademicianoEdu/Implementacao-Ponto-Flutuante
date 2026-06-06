import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada dos dados
        System.out.print("Digite o primeiro número decimal: ");
        double numA = scanner.nextDouble();

        System.out.print("Digite o segundo número decimal: ");
        double numB = scanner.nextDouble();

        System.out.println("\n--- Conversão Individual ---");
        
        // Converte o primeiro número
        String[] resA = CalculaIEEE754.converterParaBinario(numA);
        System.out.println("\nAnalisando o número A: " + numA);
        System.out.println("Sinal:    " + resA[0] + (resA[0].equals("1") ? " (Negativo)" : " (Positivo)"));
        System.out.println("Expoente: " + resA[1]);
        System.out.println("Mantissa: " + resA[2]);
        System.out.println("Binário 32 bits: " + resA[0] + " " + resA[1] + " " + resA[2]);

        // Converte o segundo
        String[] resB = CalculaIEEE754.converterParaBinario(numB);
        System.out.println("\nAnalisando o número B: " + numB);
        System.out.println("Sinal:    " + resB[0] + (resB[0].equals("1") ? " (Negativo)" : " (Positivo)"));
        System.out.println("Expoente: " + resB[1]);
        System.out.println("Mantissa: " + resB[2]);
        System.out.println("Binário 32 bits: " + resB[0] + " " + resB[1] + " " + resB[2]);

        // Operação de soma e comparação
        System.out.println("\n==================================================");
        double somaReal = numA + numB;

        System.out.println("Operação solicitada: " + numA + " + " + numB);
        System.out.println("Resultado Decimal (Operação normal do Java): " + somaReal);

        // Converte o resultado da soma de volta para o padrão IEEE 754
        String[] resSoma = CalculaIEEE754.converterParaBinario(somaReal);
        System.out.println("\nResultado convertido para formato IEEE 754 (Calculadora):");
        System.out.println(resSoma[0] + " " + resSoma[1] + " " + resSoma[2]);

        System.out.println("==================================================");
        scanner.close();
    }
}