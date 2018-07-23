package co.com.s4n.drones.dominio.vo

sealed trait Movimiento
case class A() extends Movimiento
case class I() extends Movimiento
case class D() extends Movimiento
case class M() extends Movimiento