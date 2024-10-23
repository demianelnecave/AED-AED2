package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.minutos = minutos;
        this.hora = hora;
    }

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        return this.hora + ":" + this.minutos;
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = otro == null;
        boolean otroEsDeOtra = this.getClass() != otro.getClass();
        if (otroEsDeOtra || otroEsNull) {
            return false;
        } else {
            Horario otroHorario = (Horario) otro;
            return this.hora == otroHorario.hora && this.minutos == otroHorario.minutos;
        }
    }

}
