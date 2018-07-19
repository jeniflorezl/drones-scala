package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.entidades.{Dron, Entrega, Posicion}
import co.com.s4n.drones.dominio.vo.{Movimiento, N}

import scala.util.Try

sealed trait ServicioEntrega {
  def prepararEntregas(entregas: List[String]): Option[List[List[Movimiento]]]
  def partirEntregas(entregas: List[List[Movimiento]]): Option[Iterator[List[List[Movimiento]]]]
  def realizarEntrega(entregas: Iterator[List[List[Movimiento]]])
  def ejecutarMovimiento(dron: Dron): Try[List[Dron]]
  def definirMovimiento(dron: Dron, rutas: List[Movimiento]): Try[Dron]
  def reportarMovimiento(dron: Dron): Try[Boolean]
}

object ServicioEntregaObj extends ServicioEntrega {
  override def prepararEntregas(entregas: List[String]): Option[List[List[Movimiento]]] = {
    Some(ServicioRutaObj.prepararRutas(entregas))
  }

  override def partirEntregas(entregas: List[List[Movimiento]]): Option[Iterator[List[List[Movimiento]]]] = {
    Some(entregas.grouped(3))
  }

  override def realizarEntrega(entregas: Iterator[List[List[Movimiento]]]): Unit = {
    entregas.foreach(entrega => {
      val nuevoDron = Dron(Posicion(0,0,N()),"")

    })

  }

  override def ejecutarMovimiento(dron: Dron, rutas: List[List[Movimiento]]): Try[List[Dron]] = {
    for {
      ruta <- rutas
      ruta match {
      case A() => ServicioDronObj.adelante()
    }
    }
  }

  override def definirMovimiento(dron: Dron, rutas: List[Movimiento]): Try[Dron] = {
    for
  }


  override def reportarMovimiento(dron: Dron): Try[Boolean] = ???
}
