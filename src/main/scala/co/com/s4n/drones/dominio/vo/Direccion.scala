package co.com.s4n.drones.dominio.vo

sealed trait Direccion
case class N() extends Direccion
case class E() extends Direccion
case class S() extends Direccion
case class O() extends Direccion
