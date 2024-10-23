package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] arrayRecordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this.arrayRecordatorios = new Recordatorio[0];
    }

    public int longitud() {
        return this.arrayRecordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoArray = new Recordatorio[this.arrayRecordatorios.length + 1];
        for (int k = 0; k < arrayRecordatorios.length; k++) {
            nuevoArray[k] = arrayRecordatorios[k];
        }
        nuevoArray[this.arrayRecordatorios.length] = i;
        this.arrayRecordatorios = nuevoArray;
    }

    public Recordatorio obtener(int i) {
        return this.arrayRecordatorios[i];
    }

    public void quitarAtras() {
        Recordatorio[] nuevoArray = new Recordatorio[this.arrayRecordatorios.length - 1];
        for (int k = 0; k < arrayRecordatorios.length - 1; k++) {
            nuevoArray[k] = arrayRecordatorios[k];
        }
        this.arrayRecordatorios = nuevoArray;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.arrayRecordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.arrayRecordatorios = new Recordatorio[vector.arrayRecordatorios.length]; // y si pomgo lenght = 0 y en vez
                                                                                      // de modPosi() hago
                                                                                      // agregAtras()?? es lo mismo en
                                                                                      // cuanto a eficiencia?
        for (int i = 0; i < vector.arrayRecordatorios.length; i++) { // En este reservo una vez memoria en el heap, en
                                                                     // el otro muchas veces mas al llamar a
                                                                     // agregAtras()
            this.modificarPosicion(i, vector.arrayRecordatorios[i]);
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios copiaArray = new ArregloRedimensionableDeRecordatorios(this);
        return copiaArray; // es mejor no guardar en variable?
    }
}
