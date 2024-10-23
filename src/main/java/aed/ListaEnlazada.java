package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    Nodo primero;
    Nodo ultimo;
    int longitud;

    private class Nodo {
        Nodo siguiente;
        Nodo anterior;
        T valor;

        Nodo(T v) {
            this.valor = v; // cuando lo creo no paso a dónde apunta
        }
    }

    public ListaEnlazada() {
        this.longitud = 0;
    }

    public int longitud() {
        return this.longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        nuevoNodo.siguiente = this.primero;
        if (this.longitud > 0) {
            this.primero.anterior = nuevoNodo;
            this.primero = nuevoNodo;
        } else {
            this.primero = this.ultimo = nuevoNodo;
        }
        this.longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        nuevoNodo.anterior = this.ultimo;
        // Nodo actual = this.primero;
        // while (actual.siguiente != null) {
        // actual = actual.siguiente;
        // }
        // actual.siguiente = nuevoNodo;
        if (this.longitud > 0) {
            this.ultimo.siguiente = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            this.ultimo = this.primero = nuevoNodo;
        }
        this.longitud++;
    }

    public T obtener(int i) {
        Nodo actual = this.primero;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        ;
        return actual.valor;

    }

    public void eliminar(int i) {
        if (this.longitud == 1) { // caso un solo elem
            this.primero = this.ultimo = null;
        } else {
            Nodo actual = this.primero;
            for (int j = 0; j < i; j++) {
                actual = actual.siguiente;
            }
            ;
            if (actual != this.primero && actual != this.ultimo) {
                actual.anterior.siguiente = actual.siguiente;
                actual.siguiente.anterior = actual.anterior;
            }
            if (actual == this.primero && actual != this.ultimo) {
                actual.siguiente.anterior = null;
                this.primero = actual.siguiente;
            }
            if (actual != this.primero && actual == this.ultimo) {
                actual.anterior.siguiente = null;
                this.ultimo = actual.anterior;
            }
        }
        this.longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = this.primero;
        for (int j = 0; j < indice; j++) {
            actual = actual.siguiente;
        }
        ;
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        if (lista.longitud > 0) {
            Nodo nodoActual = lista.primero;
            Nodo copiaActual = new Nodo(nodoActual.valor);
            this.primero = copiaActual;
            while (nodoActual.siguiente != null) {
                copiaActual.siguiente = new Nodo(nodoActual.siguiente.valor);
                copiaActual.siguiente.anterior = copiaActual;
                copiaActual = copiaActual.siguiente;
                nodoActual = nodoActual.siguiente;
            }
        }
        this.longitud = lista.longitud;

    }

    @Override
    public String toString() {
        String res = "[";
        Nodo nodoActual = this.primero;
        for (int i = 0; i < this.longitud - 1; i++) {
            res += nodoActual.valor + ", ";
            nodoActual = nodoActual.siguiente;
        }
        res += nodoActual.valor + "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
        int dedito;

        ListaIterador() {
            this.dedito = 0;
        }

        public boolean haySiguiente() {
            return this.dedito < ListaEnlazada.this.longitud;
        }

        public boolean hayAnterior() {
            return this.dedito - 1 >= 0;
        }

        public T siguiente() {
            // T res = this.dedito.valor;
            // this.dedito = this.dedito.siguiente;
            // return res;
            T res = ListaEnlazada.this.obtener(this.dedito);
            this.dedito++;
            return res;
        }

        public T anterior() {
            // T res = ListaEnlazada.this.obtener(this.dedito);
            this.dedito--;
            return ListaEnlazada.this.obtener(this.dedito);
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
