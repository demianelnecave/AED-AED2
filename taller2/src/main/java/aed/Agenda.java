package aed;

public class Agenda {
    private Fecha fecha;
    private ArregloRedimensionableDeRecordatorios conjRecordatorios;

    public Agenda(Fecha fechaActual) {
        this.fecha = fechaActual;
        this.conjRecordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.conjRecordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String res = this.fecha.toString() + "\n=====\n";
        for (int i = 0; i < this.conjRecordatorios.longitud(); i++) {
            if (this.conjRecordatorios.obtener(i).fecha().equals(this.fecha)) {
                res = res + this.conjRecordatorios.obtener(i).toString() + "\n";
            }
        }
        return res;
    }

    public void incrementarDia() {
        this.fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        Fecha fechaActual = new Fecha(this.fecha.dia(), this.fecha.mes());
        return fechaActual;
    }

}
