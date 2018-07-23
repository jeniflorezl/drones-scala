package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.vo._

sealed trait ServicioRuta {
  def prepararRutas(rutas: List[String]): List[List[Movimiento]]
  def definirMovimiento(movimiento: String) : List[Movimiento]
}

object ServicioRutaObj extends ServicioRuta {
  override def prepararRutas(entregas: List[String]): List[List[Movimiento]] = {
    val entregasMov = entregas.map(e => definirMovimiento(e))
    entregasMov
  }

  override def definirMovimiento(ruta: String): List[Movimiento] = {
    val movimiento: Array[String] = ruta.split("")
    val movimientos = movimiento.toList.map(mov => mov  match {
      case s: String if s == "A" => A()
      case s: String if s == "I" => I()
      case s: String if s == "D" => D()
      case _ => M()
    })
    movimientos
  }
}