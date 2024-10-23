package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {

    private Nodo _raiz;

    private int _cardinal;

    private T _mínimo;

    private T _máximo;

    private class Nodo {
        T _valor;

        Nodo _padre;

        ABB<T> _izq;

        ABB<T> _der;

        Nodo(T v) {
            _valor = v; // creo el nodo y sus dos hijos son null, tmb su padre

            _padre = null;

            _izq = new ABB<T>();

            _der = new ABB<T>();
        };
    }

    public ABB() {
        _cardinal = 0;

        _raiz = null;

        _mínimo = null;

        _máximo = null;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo() {
        return _mínimo;
    }

    public T maximo() {
        return _máximo;
    }

    public void insertar(T elem) {
        if (!pertenece(elem)) {
            Nodo nuevo = new Nodo(elem);
            if (_raiz == null) {
                _raiz = nuevo;
            } else {
                if (elem.compareTo(_raiz._valor) > 0) {
                    this._raiz._der.insertar(elem);
                } else {
                    this._raiz._izq.insertar(elem);
                }
            }
            _cardinal++;
            if (_máximo == null || elem.compareTo(_máximo) > 0) {
                _máximo = elem;
            }
            ;
            if (_mínimo == null || elem.compareTo(_mínimo) < 0) {
                _mínimo = elem;
            }
            ;
        }
    }

    public boolean pertenece(T elem) {
        return _raiz != null && (_raiz._valor == elem || pasoRecursivo(elem));
    }

    private boolean pasoRecursivo(T elem) {
        if (elem.compareTo(_raiz._valor) > 0) {
            return _raiz._der.pertenece(elem);
        } else {
            return _raiz._izq.pertenece(elem);
        }
    }

    public void eliminar(T elem) {
        if (pertenece(elem)) {
            if (_raiz._valor == elem) { // cuando lo encuentro (caso base)
                if (_raiz._padre != null) {
                    if (_raiz._der == _raiz._izq) {
                        if (_raiz._valor.compareTo(_raiz._padre._valor) > 0) {
                            _raiz._padre._der = null;
                        } else {
                            _raiz._padre._izq = null;
                        }
                    }
                    if (_raiz._der != null && _raiz._izq == null) {
                        _raiz._der._raiz._padre = _raiz._padre;
                        if (_raiz == _raiz._padre._der._raiz) {
                            _raiz._padre._der._raiz = _raiz._der._raiz;
                        } else {
                            _raiz._padre._izq._raiz = _raiz._der._raiz;
                        }
                    }
                    if (_raiz._izq != null && _raiz._der == null) {
                        _raiz._izq._raiz._padre = _raiz._padre;
                        if (_raiz == _raiz._padre._der._raiz) {
                            _raiz._padre._der._raiz = _raiz._izq._raiz;
                        } else {
                            _raiz._padre._izq._raiz = _raiz._izq._raiz;
                        }
                    }
                    if (_raiz._izq != null && _raiz._der != null) {
                        if (_raiz == _raiz._padre._der._raiz) {
                            _raiz._padre._der._raiz = sucesor(_raiz);
                        } else {
                            _raiz._padre._izq._raiz = sucesor(_raiz);
                        }
                    }
                } else {
                    if (_raiz._der == _raiz._izq) {

                    }
                }
            } else { // paso recursivo
                if (elem.compareTo(_raiz._valor) > 0) {
                    _raiz._der.eliminar(elem);
                } else {
                    _raiz._izq.eliminar(elem);
                }
            }
        }
        ;
    }

    private Nodo sucesor(Nodo nodo) {
        Nodo nodoActual = nodo._der._raiz;
        while (nodoActual._izq._raiz != null) {
            nodoActual = nodoActual._izq._raiz;
        }
        return nodoActual;
    }

    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
