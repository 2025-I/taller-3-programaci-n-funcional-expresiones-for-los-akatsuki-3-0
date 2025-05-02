package taller

object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)
  type Maniobra = List[Movimiento]

  sealed trait Movimiento

  case class Uno(n: Int) extends Movimiento

  case class Dos(n: Int) extends Movimiento


  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = m match {
    case Uno(n) if n > 0 =>
      val (principal, uno, dos) = e
      val (newPrincipal, movidos) = principal.splitAt(principal.length - n)
      (newPrincipal, uno ++ movidos, dos)

    case Uno(n) if n < 0 =>
      val (principal, uno, dos) = e
      val (movidos, newUno) = uno.splitAt(-n)
      (principal ++ movidos, newUno, dos)

    case Dos(n) if n > 0 =>
      val (principal, uno, dos) = e
      val (newPrincipal, movidos) = principal.splitAt(principal.length - n)
      (newPrincipal, uno, dos ++ movidos)

    case Dos(n) if n < 0 =>
      val (principal, uno, dos) = e
      val (movidos, newDos) = dos.splitAt(-n)
      (principal ++ movidos, uno, newDos)

    case _ => e
  }

  def aplicarMovimientos(e: Estado, movs: List[Movimiento]): List[Estado] = {
    @annotation.tailrec
    def aplicarMovimientosAux(movs: List[Movimiento], acc: List[Estado]): List[Estado] = {
      movs match {
        case Nil => acc.reverse
        case mov :: tail =>
          val nuevoEstado = aplicarMovimiento(acc.head, mov)
          aplicarMovimientosAux(tail, nuevoEstado :: acc)
      }
    }

    aplicarMovimientosAux(movs, List(e))
  }

  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    @annotation.tailrec
    def buscarSolucion(estadosPorExplorar: List[(Estado, Maniobra)], explorados: Set[Estado]): Maniobra = {
      if (estadosPorExplorar.isEmpty) {
        Nil
      } else {
        val ((estadoActual, movimientos), resto) = (estadosPorExplorar.head, estadosPorExplorar.tail)
        val (principal, uno, dos) = estadoActual

        if (principal == t2 && uno.isEmpty && dos.isEmpty) {
          movimientos.reverse
        } else if (explorados.contains(estadoActual)) {
          buscarSolucion(resto, explorados) // Estado ya explorado
        } else {

          val nuevosMovimientos = for {
            movimiento <- generarMovimientosPosibles(estadoActual)
            nuevoEstado = aplicarMovimiento(estadoActual, movimiento)
            if !explorados.contains(nuevoEstado)
          } yield (nuevoEstado, movimiento :: movimientos)

          buscarSolucion(resto ++ nuevosMovimientos, explorados + estadoActual)
        }
      }
    }

    def generarMovimientosPosibles(estado: Estado): List[Movimiento] = {
      val (principal, uno, dos) = estado

      (for {
        n <- 1 to principal.length
        if n <= principal.length
      } yield Uno(n)).toList :::
        (for {
          n <- 1 to principal.length
          if n <= principal.length
        } yield Dos(n)).toList :::
        (for {
          n <- 1 to uno.length
          if n <= uno.length
        } yield Uno(-n)).toList :::
        (for {
          n <- 1 to dos.length
          if n <= dos.length
        } yield Dos(-n)).toList
    }

    buscarSolucion(List(((t1, Nil, Nil), Nil)), Set.empty)
  }
}
