package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        // Implementar
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    @Override
    public String toString() {
        return this.dia + "/" + this.mes; // Java tipado estricto pero puedo hacer esto ¿?
    }

    @Override
    public boolean equals(Object otra) {
        boolean otroEsNull = (otra == null);
        boolean distintaClase = this.getClass() != otra.getClass();
        if (otroEsNull || distintaClase) {
            return false;
        }
        Fecha otraFecha = (Fecha) otra;
        return sonMismaFecha(this, otraFecha);
    }

    private boolean sonMismaFecha(Fecha f1, Fecha f2) {
        return f1.mes == f2.mes && f1.dia == f2.dia;
    }

    public void incrementarDia() {
        if (diasEnMes(this.mes) == this.dia) {
            this.dia = 1;
            if (this.mes == 12) {
                this.mes = 1;
            } else {
                this.mes++;
            }
        } else {
            this.dia++;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
