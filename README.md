# Taller 3 - Fundamentos de Programación Funcional y Concurrente (Maniobras de Trenes)

## 📌 Equipo de Trabajo

Universidad Del Valle 2025

| Nombre                      | Código       |
|-----------------------------|-------------|
| Gustavo Restrepo Muñoz      | 2380618     |
| Santiago Velasquez Bedoya   | 2380378     |



## Resumen del Código

Este proyecto modela y simula un sistema de maniobras ferroviarias donde un conjunto de vagones debe ser reorganizado utilizando vías auxiliares. Está implementado en **Scala** y utiliza **ScalaTest** para la validación de resultados mediante pruebas unitarias.

## Objetivo General

El sistema permite:
- Aplicar una secuencia de movimientos sobre un tren.
- Simular paso a paso el reordenamiento de los vagones.
- Determinar automáticamente una maniobra que transforme un tren inicial en otro deseado.

## Componentes Principales

### 1. **Tipos y Estructuras**
- `Vagon`: tipo genérico que representa un vagón.
- `Tren`: una lista de vagones.
- `Estado`: tupla `(principal, uno, dos)` que representa las tres vías.
- `Movimiento`: acción para mover vagones entre vías.

### 2. **Movimientos**
- `Uno(n)`: mueve `n` vagones entre vía principal y auxiliar uno.
- `Dos(n)`: mueve `n` vagones entre vía principal y auxiliar dos.
  - Si `n > 0`: desde la principal hacia la auxiliar.
  - Si `n < 0`: desde la auxiliar hacia la principal.

### 3. **Funciones Clave**
- `aplicarMovimiento`: ejecuta un solo movimiento sobre el estado actual.
- `aplicarMovimientos`: aplica una lista de movimientos y devuelve la secuencia de estados generados.
- `definirManiobra`: busca una serie de movimientos para convertir un tren inicial (`t1`) en un tren objetivo (`t2`), usando búsqueda por amplitud.

## Pruebas

Se incluye un conjunto de pruebas con distintas escalas (10, 100, 500 y 1000 vagones), verificando que los movimientos se apliquen correctamente y que todos los vagones sean procesados como se espera.

---

Este resumen sirve como base para un informe técnico más completo, donde se detallará la lógica interna, rendimiento y posibles mejoras del sistema.
