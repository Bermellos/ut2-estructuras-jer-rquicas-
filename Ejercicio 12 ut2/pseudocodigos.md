# Grimorio del Archimago - Análisis de Algoritmos

## Ejercicio 12 - Árbol Binario de Búsqueda (ABB)

### Problema
El Archimago Aldric custodia un grimorio ancestral donde registra todos los hechizos conocidos. Cada hechizo tiene un número de identificación único (entero positivo) y un nombre. El sistema debe:
1. Construir el grimorio insertando hechizos en un ABB
2. Consultar hechizos prohibidos (ID impar)
3. Generar el cántico secreto mediante recorrido in-order

---

## 1. OPERACIÓN: CONSULTAR HECHIZOS PROHIBIDOS

### Descripción
Extrae todos los hechizos cuyo ID es impar del árbol binario de búsqueda mediante un recorrido in-order. 
Los hechizos prohibidos son aquellos cuyo identificador es un número impar (id % 2 ≠ 0).

### Precondición
- El grimorio contiene hechizos insertados en el árbol ABB ordenados por ID
- Un hechizo se considera prohibido si y solo si su ID es impar
- El árbol puede estar vacío

### Postcondición
- Se retorna una lista con todos los hechizos prohibidos ordenados por ID (orden ascendente)
- La lista está vacía si no hay hechizos prohibidos en el árbol
- Los elementos del árbol no son modificados
- La lista retornada contiene referencias a los hechizos originales del grimorio

### Pseudocódigo
```
FUNCIÓN obtenerHechizosProhibidos(arbol: ABB<Hechizo>) → Lista<Hechizo>
    
    ENTRADA: Un árbol binario de búsqueda con hechizos ordenados por ID
    SALIDA: Lista de hechizos prohibidos en orden ascendente
    
    hechizosPro hibidos ← lista vacía
    
    FUNCIÓN recorrer(nodo: Nodo<Hechizo>)
        SI nodo == NULO
            RETORNAR
        FIN SI
        
        // Recorrer izquierda (menores IDs)
        recorrer(nodo.hijoIzquierdo)
        
        // Procesar nodo actual
        SI nodo.dato.id MOD 2 ≠ 0  // Si es impar
            hechizosPro hibidos.agregar(nodo.dato)
        FIN SI
        
        // Recorrer derecha (mayores IDs)
        recorrer(nodo.hijoDerecho)
    FIN FUNCIÓN
    
    // Iniciar recorrido desde la raíz
    recorrer(arbol.obtenerRaiz())
    
    RETORNAR hechizosPro hibidos
    
FIN FUNCIÓN
```

### Análisis de Complejidad de Tiempo

**Complejidad General: O(n)** donde n es la cantidad de nodos en el árbol

**Desglose:**
- Recorrido in-order: O(n) - Se visita cada nodo exactamente una vez
  - Llamadas recursivas: O(n)
  - Trabajo por nodo:
    - Verificación if (nodo == NULO): O(1)
    - Operación módulo (id % 2): O(1)
    - Agregar a lista: O(1) amortizado
- Total: O(n) × O(1) = O(n)

**Análisis de Casos:**
- Mejor caso: O(n) - Se deben visitar todos los nodos
- Caso promedio: O(n) - Se recorren todos los nodos
- Peor caso: O(n) - Se recorren todos los nodos

**Ejemplo con los datos del ejercicio:**
```
IDs ordenados: 9, 17, 25, 31, 42, 50, 58, 65, 73, 88
Prohibidos (impares): 9, 17, 25, 31, 65, 73
Total de comparaciones: 10 (todos los nodos)
Operaciones de validez: 6 (hechizos prohibidos)
```

---

## 2. OPERACIÓN: GENERAR CÁNTICO SECRETO

### Descripción
Genera un cántico secreto concatenando los nombres de todos los hechizos prohibidos en orden ascendente 
de ID, separados por " - ". Utiliza recorrido in-order para garantizar el orden.

### Precondición
- El grimorio contiene al menos un hechizo prohibido (ID impar)
- Los hechizos tienen nombres válidos (no nulos, strings bien formados)
- El árbol está correctamente balanceado o desbalanceado según el orden de inserción

### Postcondición
- Se retorna un string con los nombres de hechizos prohibidos separados por " - "
- Los nombres aparecen en orden ascendente de ID del hechizo
- Si no hay hechizos prohibidos, se retorna un string vacío
- El cántico es determinístico: los mismos hechizos siempre generan el mismo cántico

### Pseudocódigo
```
FUNCIÓN generarCantico(arbol: ABB<Hechizo>) → String
    
    ENTRADA: Un árbol binario de búsqueda con hechizos ordenados por ID
    SALIDA: String con los nombres de hechizos prohibidos separados por " - "
    
    cantico ← string vacío
    primerProhibido ← VERDADERO
    
    FUNCIÓN recorrer(nodo: Nodo<Hechizo>)
        SI nodo == NULO
            RETORNAR
        FIN SI
        
        // Recorrer izquierda (IDs menores)
        recorrer(nodo.hijoIzquierdo)
        
        // Procesar nodo actual
        SI nodo.dato.id MOD 2 ≠ 0  // Si es impar (prohibido)
            SI primerProhibido == FALSO
                cantico ← cantico + " - "
            FIN SI
            cantico ← cantico + nodo.dato.nombre
            primerProhibido ← FALSO
        FIN SI
        
        // Recorrer derecha (IDs mayores)
        recorrer(nodo.hijoDerecho)
    FIN FUNCIÓN
    
    // Iniciar recorrido desde la raíz
    recorrer(arbol.obtenerRaiz())
    
    RETORNAR cantico
    
FIN FUNCIÓN
```

### Análisis de Complejidad de Tiempo

**Complejidad General: O(n + m·L)** 
Donde:
- n = cantidad total de nodos en el árbol
- m = cantidad de hechizos prohibidos
- L = longitud promedio de los nombres

**Desglose:**
- Recorrido in-order: O(n) - Se visita cada nodo exactamente una vez
- Procesamiento por nodo:
  - Verificación if (nodo == NULO): O(1)
  - Verificación if (esProhibido): O(1)
  - Verificación if (primerProhibido): O(1)
  - Concatenación de string: O(L) en el peor caso (donde L es la longitud del nombre)
  - Actualización de bandera: O(1)
- Total: O(n × 1) + O(m × L) = O(n + m·L)

**Simplificación:**
En la práctica, considerando que L es constante o muy pequeña:
- Complejidad efectiva: O(n) con constante m·L

**Análisis de Casos:**
- Mejor caso: O(n + L) - Sin hechizos prohibidos, pero se revisan todos
- Caso promedio: O(n + m·L) - Se revisan n nodos, m son prohibidos
- Peor caso: O(n + n·L) - Todos los hechizos son prohibidos

**Ejemplo con los datos del ejercicio:**
```
Total de nodos: 10
Hechizos prohibidos: 6
Promedio de longitud de nombres: ~12 caracteres

Operaciones:
- Recorrido: 10 nodos × O(1) = O(10)
- Concatenación: 6 nombres × ~12 chars = ~72 caracteres procesados
- Comparaciones: 10 (verificación de paridad)

Resultado: "Invisibility - Ice Lance - Heal - Levitate - Shield - Summon"
```

---

## 3. ANÁLISIS ADICIONAL

### Complejidad del Árbol Binario de Búsqueda

Para las operaciones anteriores, la complejidad depende de la altura del árbol:

**Árbol Balanceado:**
- Altura: O(log n)
- Acceso a nodos: O(log n)
- Inserción: O(log n)
- Búsqueda: O(log n)

**Árbol Desbalanceado (peor caso - cadena):**
- Altura: O(n)
- Las operaciones lineales siguen siendo O(n) ya que visitamos todos los nodos

**Con datos del ejercicio:**
El orden de inserción es: 42, 17, 58, 9, 31, 73, 25, 50, 65, 88
Esto crea un árbol relativamente balanceado con altura ~4

### Resumen de Complejidades

| Operación | Mejor Caso | Caso Promedio | Peor Caso |
|-----------|-----------|---------------|-----------|
| obtenerHechizosProhibidos() | O(n) | O(n) | O(n) |
| generarCantico() | O(n+L) | O(n+m·L) | O(n+n·L) |
| buscar() | O(log n) | O(log n) | O(n) |
| insertar() | O(log n) | O(log n) | O(n) |

Donde:
- n = cantidad de hechizos en el grimorio
- m = cantidad de hechizos prohibidos
- L = longitud promedio de los nombres

---

## 4. CONCLUSIÓN

Ambas operaciones tienen complejidad lineal O(n) porque necesitan procesar potencialmente todos los nodos 
del árbol en un recorrido in-order. La estructura de árbol binario de búsqueda garantiza que este recorrido 
retorna los elementos en orden, lo que es fundamental para las operaciones del grimorio.

El cántico secreto es una concatenación de strings que, en el peor caso, puede tener complejidad adicional 
debido a la operación de string concatenation, pero esta sigue siendo manejable en la práctica.
