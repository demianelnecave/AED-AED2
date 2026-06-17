## Trabajo Práctico de complejidad y estructuras de datos para la materia Algoritmos y Estructuras de Datos (AED)

### Miembros: Lucio Schraier, Demián Elnecavé, Lautaro Oldani

### Enunciado
La empresa de logística y distribucíon BestEffort1 S.A. hace lo mejor posible para entregar todos los pedidos que recibe
en tiempo y forma. Esta empresa opera en diversas ciudades de la provincia de Buenos Aires. Cada pedido define un traslado
de productos de una ciudad a otra.

La red de ciudades en las que opera BestEffort define un grafo completo2
, es decir: hay un camino directo entre cada
par de ciudades. Además, esos caminos directos resultan ser los de menor costo y por lo tanto la eleccíon natural para los
traslados.

Se requiere diseñar e implementar un sistema que permita:

• Registrar traslados.


• Despachar traslados: BestEffort adopta una política que permite regular dinámicamente el despacho de los traslados
más redituables y aquellos que están pendientes de entrega hace más tiempo (esto impide lo que se conoce como
inanicíon).

• Recolectar estadísticas de las ciudades.

#### Aclaraciones
Cada ciudad se identifica con un entero no negativos, son consecutivos y van de 0 a cantidadDeCiudades-1.
Cada traslado:
• Tiene un identificador único (entero no negativo)
• Involucra exactamente a dos ciudades distintas: origen y destino.
• Tiene una ganancia neta asociada (entero positivo).
• Tiene un timestamp4
(entero no negativo), que corresponde al momento en el que el cliente hizo el pedido. Son
valores únicos.

Dados dos traslados t1 y t2, decimos que t1 es más redituable que t2 si se cumple alguna de las siguientes condiciones:
• t1.ganancia > t2.ganancia
• t1.ganancia == t2.ganancia ∧ t1.id < t2.id

Dados dos traslados t1 y t2, decimos que t1 es más antiguo que t2 si se cumple que t1.timestamp < t2.timestamp
Las estadísticas aplican sobre los traslados despachados. Es decir, un traslado empieza a aportar informacíon estadística
a partir del momento en el que es seleccionado por el sistema para ser despachado.

Las estadísticas deben devolver:

• La/s ciudad/es con mayor ganancia. La ganancia de una ciudad es la suma de las ganancias de todos los traslados
que se originaron en esa ciudad.

• La/s ciudad/es con mayor pérdida. La pérdida de una ciudad es la suma de las ganancias de todos los traslados
destinados a dicha ciudad.

• La ciudad con mayor superávit: ganancia−perdida. En caso de empate, devuelve la que tiene menor identificador.

• La ganancia promedio por traslado a nivel global.

En caso de requerir un arreglo redimensionable, consideraremos que la complejidad agregar un elemento al final es
considerada O(1).

• Más precisamente, es O(1) amortizado.

• Como la redimensíon es poco frecuente, sólo sucede cuando se excede el tamaño actual...

• Esto quiere decir que -si bien el momento de la copia induce un costo proporcional a la cantidad de elementos-,
ese costo puede ser pensado como si estuviera promediado en el costo de cada insercíon, cuya complejidad O(1).

• Considerar que es O(1) en peor caso es incorrecto, pero en el contexto de este TP ignoraremos este problema y
aceptaremos contarlo como O(1).

• No pueden asumir que esta simplificacíon valga en otras instancias de evaluacíon.

• Ver el Anexo para una explicacíon de la clase ArrayList, que implementa una secuencia usando un arreglo
redimensionable.

### Operaciones a implementar con sus restricciones de complejidad
Sea InfoTraslado = <id: N, origen: N, destino: N, gananciaNeta: N, timestamp: N>
1. nuevoSistema(in cantCiudades: N, in traslados: seq<InfoTraslado>): BestEffort O(|C| + |T|)
inicializa el sistema de la empresa BestEffort.
Requiere cantCiudades > 0. Los traslados deben tener ids distintos entre si, los origenes y destinos están entre 0 y
cantCiudades-1, los gananciaNeta y timestamp son positivos.
2. registrarTraslados(inout sistema: BestEffort, in traslados: seq<InfoTraslado>) O(|traslados| log(|T|))
incorpora todos los traslados al sistema.
Requiere que los traslados tengan id distinto entre si, y con respecto con los traslados ya registrados anteriormente.
3. despacharMasRedituables(inout sistema: BestEffort, in n: N): seq<N> O(n (log(|T|) + log(|C|)))
devuelve los identificadores de los n traslados más redituables (y los elimina del sistema), ordenados de forma decreciente
por ganancia. En caso de que n sea mayor a la cantidad de traslados por despachar, se despachan todos.
4. despacharMasAntiguos(inout sistema: BestEffort, in n: N): seq<N> O(n (log(|T|) + log(|C|)))
devuelve los identificadores de los n traslados más antiguos (y los elimina del sistema), ordenados de forma creciente
por timestamp. En caso de que n sea mayor a la cantidad de traslados por despachar, se despachan todos.
5. ciudadConMayorSuperavit(in sistema: BestEffort): N O(1)
devuelve el identificador de la ciudad con mayor superávit, en caso de empate devuelve la que tiene menor identificador.
6. ciudadesConMayorGanancia(in sistema: BestEffort): seq<N> O(1)
devuelve una secuencia con el identificador de la ciudad (o los identificadores de las ciudades, en caso de empate) con
mayor ganancia.
7. ciudadesConMayorPerdida(in sistema: BestEffort): seq<N> O(1)
devuelve una secuencia con el identificador de la ciudad (o los identificadores de las ciudades, en caso de empate) con
mayor pérdida.
8. gananciaPromedioPorTraslado(in sistema: BestEffort): N O(1)
devuelve la parte entera de la ganancia promedio por traslado a nivel global.
Requiere que se haya despachado al menos un traslado.
