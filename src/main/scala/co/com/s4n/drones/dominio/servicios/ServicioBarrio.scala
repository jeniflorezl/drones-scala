package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.entidades.{Barrio, Dron}

import scala.util.{Failure, Success, Try}

sealed trait ServicioBarrio {
  def validarPosicion(dron: Dron, barrio: Barrio): Try[Dron]
}

object ServicioBarrioObj extends ServicioBarrio {
  override def validarPosicion(dron: Dron, barrio: Barrio): Try[Dron] = {
    if (dron.posicion.x <= barrio.elimite && dron.posicion.x >= barrio.olimite &&
      dron.posicion.y <= barrio.nlimite && dron.posicion.y >= barrio.slimite)
      Success(dron)
    else
      Failure(new Exception("Posición del dron invalida"))
  }
}
