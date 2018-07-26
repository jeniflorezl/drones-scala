package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.vo._

import scala.util.{Failure, Success, Try}

sealed trait ServicioRuta {
  def prepararRutas(rutas: List[String]): List[List[Movimiento]]
  def definirMovimiento(movimiento: String) : List[Try[Movimiento]]
}

object ServicioRutaObj extends ServicioRuta {
  override def prepararRutas(entregas: List[String]): List[List[Movimiento]] = {
    val entregasMov = entregas.map(e => definirMovimiento(e).filter(movimiento => movimiento.isSuccess))
    entregasMov.map(listaMovimientos => listaMovimientos.map(movimiento => movimiento.get))
  }

  override def definirMovimiento(ruta: String): List[Try[Movimiento]] = {
    val arrayRuta: Array[String] = ruta.split("")
    val movimientos = arrayRuta.toList.map(mov => mov  match {
      case s: String if s == "A" => Success(A())
      case s: String if s == "I" => Success(I())
      case s: String if s == "D" => Success(D())
      case _ => Failure(new Exception("caracter invalido"))
    })
    movimientos
  }
}