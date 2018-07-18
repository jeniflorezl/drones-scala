package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.entidades.{Dron, Posicion}
import co.com.s4n.drones.dominio.vo.{E, N, O, S}

sealed trait ServicioDron {
  def adelante(dron: Dron): Dron
  def derecha(dron: Dron) : Dron
  def izquierda(dron: Dron) : Dron
}

object ServicioDronObj extends ServicioDron {
  override def adelante(dron: Dron): Dron = {
    var x = dron.posicion.x
    var y = dron.posicion.y
    dron.posicion.direccion match {
      case N() => y+=1
      case E() => x+=1
      case S() => y-=1
      case O() => x-=1
    }
    Dron(Posicion(x, y, dron.posicion.direccion), dron.file, dron.entregas)
  }

  override def derecha(dron: Dron): Dron = {
    var direccion = dron.posicion.direccion
    direccion match {
      case N() => direccion = E()
      case E() => direccion = S()
      case S() => direccion = O()
      case O() => direccion = N()
    }
    Dron(Posicion(dron.posicion.x, dron.posicion.y, direccion), dron.file, dron.entregas)
  }

  override def izquierda(dron: Dron): Dron = {
    var direccion = dron.posicion.direccion
    direccion match {
      case N() => direccion = O()
      case E() => direccion = N()
      case S() => direccion = E()
      case O() => direccion = S()
    }
    Dron(Posicion(dron.posicion.x, dron.posicion.y, direccion), dron.file, dron.entregas)
  }
}

