# Grimorio del Archimago - Ejercicio 12

## Descripción del Proyecto

Este proyecto implementa el **Grimorio del Archimago**, un sistema para almacenar y gestionar hechizos usando un **Árbol Binario de Búsqueda (ABB)**. El grimorio permite:

1. **Insertar hechizos** ordenados automáticamente por ID
2. **Consultar hechizos prohibidos** (aquellos con ID impar)
3. **Generar un cántico secreto** concatenando los nombres de hechizos prohibidos en orden

## Estructura del Proyecto

```
Ejercicio 12 ut2/
├── src/main/java/
│   ├── ucu/edu/aed/
│   │   ├── Main.java                    # Programa principal
│   │   ├── dominio/
│   │   │   ├── Hechizo.java             # Clase que representa un hechizo
│   │   │   └── Grimorio.java            # Clase principal del grimorio
│   │   └── tda/
│   │       ├── TDAArbolBinario.java      # Interfaz del árbol (base)
│   │       ├── TDAElemento.java         # Interfaz del nodo (base)
│   │       ├── ArbolBinarioBusqueda.java # Implementación del ABB
│   │       └── Nodo.java                # Implementación del nodo
├── pom.xml                              # Configuración de Maven
├── pseudocodigos.md                     # Análisis de algoritmos y pseudocódigo
└── README.md                            # Este archivo
```

## Hechizos en el Grimorio

Los siguientes hechizos se insertan en el orden especificado:

| ID | Nombre | Prohibido |
|----|--------|-----------|
| 42 | Fireball | ❌ |
| 17 | Ice Lance | ✅ |
| 58 | Thunder | ❌ |
| 9 | Invisibility | ✅ |
| 31 | Levitate | ✅ |
| 73 | Summon | ✅ |
| 25 | Heal | ✅ |
| 50 | Teleport | ❌ |
| 65 | Shield | ✅ |
| 88 | Curse | ❌ |

## Características Implementadas

### Clase `Hechizo`
- Representa un hechizo con ID y nombre
- Implementa `Comparable<Hechizo>` para comparación por ID
- Método `esProhibido()` determina si el ID es impar

### Clase `Nodo<T>`
- Implementa `TDAElemento<T>` para representar nodos del árbol
- Soporta operaciones recursivas: buscar, insertar, eliminar
- Implementa recorridos: in-order, pre-order, post-order
- Métodos adicionales: altura, nivel, cantidad de hojas

### Clase `ArbolBinarioBusqueda<T>`
- Implementa `TDAArbolBinario<T>`
- Gestiona la raíz del árbol
- Delega operaciones recursivas al nodo raíz

### Clase `Grimorio`
- Orquesta las operaciones del grimorio
- **`obtenerHechizosProhibidos()`**: Retorna lista de hechizos prohibidos
  - Pseudocódigo y análisis: Ver `pseudocodigos.md`
  - Complejidad: **O(n)**
- **`generarCantico()`**: Genera el cántico concatenando nombres
  - Pseudocódigo y análisis: Ver `pseudocodigos.md`
  - Complejidad: **O(n + m·L)** donde m = hechizos prohibidos, L = longitud promedio

## Compilación y Ejecución

### Requisitos
- Java 8 o superior
- Maven 3.6 o superior

### Compilar
```bash
cd "Ejercicio 12 ut2"
mvn clean compile
```

### Ejecutar
```bash
# Opción 1: Usando Java directamente
mvn package
java -cp target/classes ucu.edu.aed.Main

# Opción 2: Desde el directorio target/classes
cd target/classes
java ucu.edu.aed.Main
```

### Salida Esperada
```
Insertando hechizos en el grimorio...

✓ Insertado: Fireball (ID: 42)
✓ Insertado: Ice Lance (ID: 17)
✓ Insertado: Thunder (ID: 58)
✓ Insertado: Invisibility (ID: 9)
✓ Insertado: Levitate (ID: 31)
✓ Insertado: Summon (ID: 73)
✓ Insertado: Heal (ID: 25)
✓ Insertado: Teleport (ID: 50)
✓ Insertado: Shield (ID: 65)
✓ Insertado: Curse (ID: 88)

=== GRIMORIO DEL ARCHIMAGO ===
Total de hechizos: 10
Hechizos en orden:
  Invisibility (ID: 9)
  Ice Lance (ID: 17)
  Heal (ID: 25)
  Levitate (ID: 31)
  Fireball (ID: 42)
  Teleport (ID: 50)
  Thunder (ID: 58)
  Shield (ID: 65)
  Summon (ID: 73)
  Curse (ID: 88)

--- HECHIZOS PROHIBIDOS ---
  Invisibility (ID: 9)
  Ice Lance (ID: 17)
  Heal (ID: 25)
  Levitate (ID: 31)
  Shield (ID: 65)
  Summon (ID: 73)

--- CÁNTICO SECRETO ---
  Invisibility - Ice Lance - Heal - Levitate - Shield - Summon
```

## Análisis de Algoritmos

Para análisis detallado de pseudocódigo, precondiciones, postcondiciones y complejidad temporal, 
consultar el archivo `pseudocodigos.md`.

### Resumen de Complejidades

- **obtenerHechizosProhibidos()**: O(n)
- **generarCantico()**: O(n + m·L)
- **insertar()**: O(log n) - promedio, O(n) - peor caso
- **buscar()**: O(log n) - promedio, O(n) - peor caso

## Conceptos Aplicados

1. **Tipos de Datos Abstractos (TDA)**
   - Interfaz `TDAArbolBinario<T>`
   - Interfaz `TDAElemento<T>`

2. **Árbol Binario de Búsqueda (ABB)**
   - Inserción manteniendo orden
   - Búsqueda eficiente
   - Recorridos: in-order, pre-order, post-order

3. **Genericidad en Java**
   - Clases genéricas: `Nodo<T>`, `ArbolBinarioBusqueda<T>`
   - Interfaz `Comparable<T>`

4. **Recursión**
   - Todas las operaciones del árbol son recursivas
   - Tratamiento de casos base (nodos nulos)

5. **Análisis de Complejidad**
   - Notación O grande
   - Casos: mejor, promedio, peor
   - Análisis amortizado

## Autor
Implementación para el curso de Algoritmos y Estructuras de Datos (AED)

## Licencia
Proyecto educativo - Universidad Católica del Uruguay (UCU)
