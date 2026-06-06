public class CalculaIEEE754 {

    // Mantemos o método estático pois ele atua como uma função utilitária pura
    public static String[] converterParaBinario(double numero) {
        if (numero == 0.0) {
            return new String[]{"0", "00000000", "00000000000000000000000"};
        }

        // 1. Definir o Sinal
        String sinal = (numero < 0) ? "1" : "0";
        numero = Math.abs(numero);

        // 2. Separar parte inteira e fracionária
        long parteInteira = (long) numero;
        double parteFracionaria = numero - parteInteira;

        // 3. Converter parte inteira para binário
        StringBuilder binInteiro = new StringBuilder();
        if (parteInteira == 0) {
            binInteiro.append("0");
        } else {
            long temp = parteInteira;
            while (temp > 0) {
                binInteiro.insert(0, temp % 2);
                temp = temp / 2;
            }
        }

        // 4. Converter parte fracionária para binário
        StringBuilder binFracionario = new StringBuilder();
        double tempFrac = parteFracionaria;
        for (int i = 0; i < 50; i++) {
            tempFrac *= 2;
            if (tempFrac >= 1) {
                binFracionario.append("1");
                tempFrac -= 1;
            } else {
                binFracionario.append("0");
            }
            if (tempFrac == 0) break;
        }

        // 5. Normalização
        int expoenteReal = 0;
        String mantissaCrua = "";

        if (parteInteira > 0) {
            expoenteReal = binInteiro.length() - 1;
            mantissaCrua = binInteiro.substring(1) + binFracionario.toString();
        } else {
            int primeiroUm = binFracionario.indexOf("1");
            expoenteReal = -(primeiroUm + 1);
            mantissaCrua = binFracionario.substring(primeiroUm + 1);
        }

        // 6. Calcular o Expoente com Viés (127)
        int expoenteVies = expoenteReal + 127;
        StringBuilder binExpoente = new StringBuilder();
        int tempExp = expoenteVies;
        for (int i = 0; i < 8; i++) {
            binExpoente.insert(0, tempExp % 2);
            tempExp = tempExp / 2;
        }

        // 7. Ajustar a Mantissa para exatos 23 bits
        String mantissa = "";
        if (mantissaCrua.length() >= 23) {
            mantissa = mantissaCrua.substring(0, 23);
            
            if (mantissaCrua.length() > 23 && mantissaCrua.charAt(23) == '1') {
                int mantissaInt = Integer.parseInt(mantissa, 2) + 1;
                mantissa = Integer.toBinaryString(mantissaInt);
                
                while (mantissa.length() < 23) {
                    mantissa = "0" + mantissa;
                }
                if (mantissa.length() > 23) {
                    mantissa = mantissa.substring(1);
                }
            }
        } else {
            StringBuilder pad = new StringBuilder(mantissaCrua);
            while (pad.length() < 23) {
                pad.append("0");
            }
            mantissa = pad.toString();
        }

        return new String[]{sinal, binExpoente.toString(), mantissa};
    }
}