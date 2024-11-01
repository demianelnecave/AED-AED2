package aed;

class Main {
    int fibonacci(int n) {
        int res;

        if (n == 0) {
            res = 0;
        } else if (n == 1) {
            res = 1;
        } else {
            res = fibonacci(n - 1) + fibonacci(n - 2);
        }

        return res;
    }

    int fibonacciReturn(int n) {
        // Se pueden unificar estos if?       CHECKED
        if (n == 0 || n==1) {
            return n;
        }
        return fibonacciReturn(n - 1) + fibonacciReturn(n - 2);
    }

    // Revisar, no pasa los tests CHECKED
    double maximo(double[] xs) {
        double res = xs[0];

        for (double x : xs) {
            // Si encuentro un elemento mayor al que tenía guardado, me lo guardo
            if (x > res) {
                res = x;
            }
        }

        return res;
    }

    int[] sumarArreglos(int[] seq1, int[] seq2) {
        int[] res = new int[seq1.length];             // CON TIPOS COMPLEJOS SE USA EL new para (instanciar?) la clase

        for (int i = 0; i < seq1.length; i++) {
            // Revisar el error
            res[i] = seq1[i] + seq2[i];
        }

        return res;
    }

    String iniciales(String texto) {
        String res = "";
        for (int i = 0; i < texto.length(); i++) {
            if (i == 0 || (texto.charAt(i-1) == ' ' && texto.charAt(i) != ' ')) {
                res += texto.charAt(i);
            }
        }
        return res;
    }
}