package co.com.s4n.drones.dominio.entidades

import co.com.s4n.drones.dominio.vo.Movimiento

case class Dron(posicion: Posicion, file: String, entregas: List[List[Movimiento]])
