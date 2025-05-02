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
}
