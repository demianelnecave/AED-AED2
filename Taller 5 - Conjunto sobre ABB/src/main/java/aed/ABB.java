package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    Nodo raiz;
    T maximo;
    T minimo;
    int cardinal;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;

        Nodo(T elem) {
            valor = elem;
        }
    }

    public ABB() {
        raiz = null;
        cardinal = 0;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo() {
        return minimo;
    }

    public T maximo() {
        return maximo;
    }

    public void insertar(T elem) {
        if (raiz != null) {
            raiz = insertarRecursivo(raiz, elem);
        } else {
            raiz = new Nodo(elem);
        }
        if (maximo == null || elem.compareTo(maximo) > 0) {
            maximo = elem;
        }
        if (minimo == null || elem.compareTo(minimo) < 0) {
            minimo = elem;
        }
        cardinal++;
        return;
    }

    private Nodo insertarRecursivo(Nodo nodo, T elem) { // al pasar un parametro se hace por copia
        if (nodo.valor.compareTo(elem) == 0) {
            cardinal--;
            return nodo;
        }
        if (elem.compareTo(nodo.valor) < 0) {
            if (nodo.izq == null) {
                nodo.izq = new Nodo(elem);
                return nodo;
            }
            nodo.izq = insertarRecursivo(nodo.izq, elem);
            return nodo;
        }
        if (nodo.der == null) {
            nodo.der = new Nodo(elem);
            return nodo;
        }
        nodo.der = insertarRecursivo(nodo.der, elem);
        return nodo;

    }

    public boolean pertenece(T elem) {
        return perteneceRecursivo(raiz, elem);
    }

    private boolean perteneceRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            return false;
        }
        if (nodo.valor.compareTo(elem) == 0) {
            return true;
        }
        if (elem.compareTo(nodo.valor) > 0) {
            return perteneceRecursivo(nodo.der, elem);
        } else {
            return perteneceRecursivo(nodo.izq, elem);
        }
    }

    private Nodo maximoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.der == null) {
            return nodo;
        }
        return maximoRecursivo(nodo.der);
    }

    private Nodo minimoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.izq == null) {
            return nodo;
        }
        return minimoRecursivo(nodo.izq);
    }

    public void eliminar(T elem) {
        raiz = eliminarRecursivo(raiz, elem);
        if (raiz != null) {

            if (maximo == elem) {
                maximo = maximoRecursivo(raiz).valor;
            }
            if (minimo == elem) {
                minimo = minimoRecursivo(raiz).valor;
            }
        } else {
            maximo = null;
            minimo = null;
        }
        cardinal--;
        return;
    }

    private Nodo eliminarRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            cardinal++;
            return null;
        }

        if (elem.compareTo(nodo.valor) < 0) {
            nodo.izq = eliminarRecursivo(nodo.izq, elem);
        } else if (elem.compareTo(nodo.valor) > 0) {
            nodo.der = eliminarRecursivo(nodo.der, elem);
        } else {
            // un solo hijo o sin hijos
            if (nodo.der == null) {
                return nodo.izq; // devuelvo el que queda (si no queda ninguno devuelvo null)
            } else if (nodo.izq == null) {
                return nodo.der;
            }
            // dos hijos
            Nodo minDerecha = minimoDerecha(nodo);
            raiz = eliminarRecursivo(raiz, minDerecha.valor);
            nodo.valor = minDerecha.valor;
            return nodo;
        }
        return nodo;
    }

    private Nodo minimoDerecha(Nodo nodo) {
        Nodo nodoActual = nodo.der;
        while (nodoActual.izq != null) {
            nodoActual = nodoActual.izq;
        }
        return nodoActual;
    }

    public String toString() {
        if (raiz == null) {
            return "{}";
        }
        ABB_Iterador it = new ABB_Iterador();
        String res = "{" + it.siguiente();
        while (it.haySiguiente()) {
            res += "," + it.siguiente();
        }
        res += "}";
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo siguiente;

        ABB_Iterador() {
            siguiente = minimoRecursivo(raiz);
        }

        public boolean haySiguiente() {
            return siguiente != null;
        }

        public T siguiente() {
            T res = siguiente.valor;
            if (siguiente.valor.compareTo(maximo) != 0) {
                siguiente = sucesor(siguiente);
            } else {
                siguiente = null;
            }
            return res;
        }

        private Nodo sucesor(Nodo nodo) {
            if (nodo.der != null) {
                return minimoDerecha(nodo);
            }
            return sucesorArriba(nodo, raiz);
        }

        private Nodo sucesorArriba(Nodo nodo, Nodo inicio) {
            if (nodo.valor.compareTo(inicio.valor) > 0) {
                return sucesorArriba(nodo, inicio.der);
            }
            // vale nodo.valor menor a inicio.valor
            if (inicio.izq.valor.compareTo(nodo.valor) <= 0) { // si me voy para la izquierda me pasé
                if (inicio.izq.der != null) { // me puedo ir a la der de la izq
                    if (inicio.izq.der.valor.compareTo(nodo.valor) > 0) { // hay un grande mas chico
                        return sucesorArriba(nodo, inicio.izq);
                    }
                }
                return inicio;
            } else { // puedo irme a la izquierda
                return sucesorArriba(nodo, inicio.izq);
            }

        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
