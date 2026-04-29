# Diagrama de Árbol Binario de Búsqueda - Grimorio del Archimago

## Árbol resultante después de insertar todos los hechizos

```
                                42 (Fireball - PAR)
                              /                      \
                            17 (Ice Lance - IMPAR)    58 (Thunder - PAR)
                          /          \                /         \
                        9            31             50          73
                  (Invisibility)  (Levitate)    (Teleport)   (Summon)
                    IMPAR          IMPAR         PAR         IMPAR
                                      \                      /    \
                                      25                   65     88
                                    (Heal)               (Shield) (Curse)
                                    IMPAR                IMPAR    PAR

```

## Detalles del Árbol

### Estructura
- **Raíz**: 42 (Fireball)
- **Altura**: 4
- **Total de nodos**: 10
- **Nodos internos**: 4
- **Hojas**: 6

### Recorrido In-order (Izquierda-Raíz-Derecha)
El recorrido in-order retorna los elementos ordenados por ID:

**Secuencia**: 
```
9 (Invisibility) → 17 (Ice Lance) → 25 (Heal) → 31 (Levitate) → 42 (Fireball) 
→ 50 (Teleport) → 58 (Thunder) → 65 (Shield) → 73 (Summon) → 88 (Curse)
```

### Recorrido Pre-order (Raíz-Izquierda-Derecha)
```
42 (Fireball) → 17 (Ice Lance) → 9 (Invisibility) → 31 (Levitate) → 25 (Heal)
→ 58 (Thunder) → 50 (Teleport) → 73 (Summon) → 65 (Shield) → 88 (Curse)
```

### Recorrido Post-order (Izquierda-Derecha-Raíz)
```
9 (Invisibility) → 25 (Heal) → 31 (Levitate) → 17 (Ice Lance) → 50 (Teleport)
→ 65 (Shield) → 88 (Curse) → 73 (Summon) → 58 (Thunder) → 42 (Fireball)
```

## Hechizos Prohibidos (ID IMPAR)

Ubicación en el árbol (resaltados):

```
                                42 (Fireball - PAR)
                              /                      \
                        ┌──> 17 *** (IMPAR)          58 (Thunder - PAR)
                       /        \                /         \
                    9 ***       31 ***          50          73 ***
              (IMPAR)         (IMPAR)          PAR         (IMPAR)
                                  \                      /    \
                                  25 ***               65 ***  88 (Curse)
                                (IMPAR)              (IMPAR)   PAR

Hechizos Prohibidos (marcados con ***):
1. ID 9  - Invisibility (nivel 2, hijo izquierdo de 17)
2. ID 17 - Ice Lance (nivel 1, hijo izquierdo de 42)
3. ID 25 - Heal (nivel 3, hijo derecho de 31)
4. ID 31 - Levitate (nivel 2, hijo derecho de 17)
5. ID 65 - Shield (nivel 3, hijo izquierdo de 73)
6. ID 73 - Summon (nivel 2, hijo derecho de 58)
```

## Cántico Secreto

El recorrido in-order de hechizos prohibidos genera:

```
Invisibility - Ice Lance - Heal - Levitate - Shield - Summon
```

**Explicación del cántico:**
- Se recorre el árbol en in-order (izquierda-raíz-derecha)
- Se incluyen solo aquellos nodos cuyo ID es impar
- Se concatenan los nombres separados por " - "
- El resultado está ordenado por ID: 9, 17, 25, 31, 65, 73

## Análisis de Profundidad

### Niveles del Árbol
```
Nivel 0 (Raíz):       42
Nivel 1:              17, 58
Nivel 2:              9, 31, 50, 73
Nivel 3:              25, 65, 88
```

### Profundidad de cada Nodo
| ID | Hechizo | Profundidad | Prohibido |
|----|---------|------------|-----------|
| 9 | Invisibility | 2 | ✅ |
| 17 | Ice Lance | 1 | ✅ |
| 25 | Heal | 3 | ✅ |
| 31 | Levitate | 2 | ✅ |
| 42 | Fireball | 0 | ❌ |
| 50 | Teleport | 2 | ❌ |
| 58 | Thunder | 1 | ❌ |
| 65 | Shield | 3 | ✅ |
| 73 | Summon | 2 | ✅ |
| 88 | Curse | 3 | ❌ |

### Hojas del Árbol
Las hojas son los nodos sin descendientes:
- ID 9 (Invisibility) - Profundidad 2
- ID 25 (Heal) - Profundidad 3
- ID 50 (Teleport) - Profundidad 2
- ID 65 (Shield) - Profundidad 3
- ID 88 (Curse) - Profundidad 3

**Total de hojas**: 5

## Operaciones de Búsqueda Ejemplo

### Buscar ID 65 (Shield)
```
Inicio en 42
  42 < 65 → ir a derecha (58)
  58 < 65 → ir a derecha (73)
  73 > 65 → ir a izquierda (65)
  65 == 65 → ENCONTRADO ✓

Comparaciones: 4
```

### Buscar ID 25 (Heal)
```
Inicio en 42
  42 > 25 → ir a izquierda (17)
  17 < 25 → ir a derecha (31)
  31 > 25 → ir a izquierda (25)
  25 == 25 → ENCONTRADO ✓

Comparaciones: 4
```

## Ventajas del ABB

1. **Inserción ordenada automática**: Los elementos se mantienen ordenados sin necesidad de ordenar explícitamente
2. **Búsqueda eficiente**: O(log n) en promedio para búsquedas
3. **Recorrido in-order**: Retorna elementos en orden con una sola pasada por el árbol
4. **Flexibilidad**: Soporta operaciones de eliminación y actualización

## Caso de Uso: Acceso a Hechizos Prohibidos

El ABB es especialmente útil para este problema porque:

1. **Inserción eficiente**: Se insertan 10 hechizos con O(10 log 10) ≈ O(33) operaciones
2. **Consulta de prohibidos rápida**: Se recorren todos los nodos una sola vez O(10)
3. **Cántico ordenado**: El in-order garantiza orden automáticamente sin ordenamiento adicional

Sin el ABB, necesitaríamos:
- Una lista sin orden: Inserción O(1), pero consulta O(n log n) tras ordenar
- Un array ordenado: Inserción O(n), búsqueda O(log n)

El ABB proporciona el mejor balance para este conjunto de operaciones.
