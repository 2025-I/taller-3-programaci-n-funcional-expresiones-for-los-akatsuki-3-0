package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ManiobrasTrenesTest extends AnyFunSuite {

  import ManiobrasTrenes._

  // Pruebas para aplicarMovimientos
  test("Pruebas de juguete: 10 vagones y 10 movimientos") {
    val estadoInicial: Estado = (List.range(1, 11), Nil, Nil)
    val movimientos: List[Movimiento] = List.fill(5)(Uno(2)) ++ List.fill(5)(Dos(-1))
    val resultado = aplicarMovimientos(estadoInicial, movimientos)
    assert(resultado.size == 11)
    assert(resultado.head == estadoInicial)
    assert(resultado.last._1.size == 0) // Todos movidos a vías auxiliares
  }

  test("Pruebas pequeñas: 100 vagones y 100 movimientos") {
    val estadoInicial: Estado = (List.range(1, 101), Nil, Nil)
    val movimientos: List[Movimiento] = List.fill(50)(Uno(2)) ++ List.fill(50)(Dos(-1))
    val resultado = aplicarMovimientos(estadoInicial, movimientos)
    assert(resultado.size == 101)
    assert(resultado.head == estadoInicial)
    assert(resultado.last._1.size == 0)
  }
  test("Pruebas medianas: 500 vagones y 500 movimientos") {
    val estadoInicial: Estado = (List.range(1, 501), Nil, Nil)
    val movimientos: List[Movimiento] = List.fill(250)(Uno(2)) ++ List.fill(250)(Dos(-1))
    val resultado = aplicarMovimientos(estadoInicial, movimientos)
    assert(resultado.size == 501)
    assert(resultado.head == estadoInicial)
    assert(resultado.last._1.size == 0)
  }

  test("Pruebas grandes: 1000 vagones y 1000 movimientos") {
    val estadoInicial: Estado = (List.range(1, 1001), Nil, Nil)
    val movimientos: List[Movimiento] = List.fill(500)(Uno(2)) ++ List.fill(500)(Dos(-1))
    val resultado = aplicarMovimientos(estadoInicial, movimientos)
    assert(resultado.size == 1001)
    assert(resultado.head == estadoInicial)
    assert(resultado.last._1.size == 0)
  }
}
