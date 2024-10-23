package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = fechaPorValor(fecha); // cuando le doy un valor al atributo me aseguro de que sea por copia
        this.horario = horario;
    }

    private Fecha fechaPorValor(Fecha fecha) {
        Fecha nuevaFecha = new Fecha(fecha.dia(), fecha.mes());
        return nuevaFecha;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {
        return fechaPorValor(this.fecha); // cuando retorne mi atributo me aseguro también de que sea por copia
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return this.mensaje + " @ " + this.fecha.toString() + " " + this.horario.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsUnNull = otro == null;
        boolean otroNoRecuerdaUnCorno = otro.getClass() != this.getClass();
        if (otroNoRecuerdaUnCorno || otroEsUnNull) {
            return false;
        } else {
            Recordatorio otroRecordatorio = (Recordatorio) otro;
            return this.mensaje == otroRecordatorio.mensaje && this.fecha.equals(otroRecordatorio.fecha)
                    && this.horario.equals(otroRecordatorio.horario);
        }
    }

}
