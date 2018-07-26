package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.entidades.{Barrio, Dron, Entrega, Posicion}
import co.com.s4n.drones.dominio.vo._

import scala.util.Try

sealed trait ServicioEntrega {
  def prepararEntregas(entregas: List[String]): Option[List[List[Movimiento]]]
  def partirEntregas(entregas: List[List[Movimiento]]): Option[Iterator[List[List[Movimiento]]]]
  def realizarEntrega(entregas: Iterator[List[List[Movimiento]]])
  def ejecutarDron(dron: Dron): Try[Dron]
  def ejecutarMovimiento(dron: Dron): Try[Dron]
  def reportarMovimiento(dron: Dron)
}

object ServicioEntregaObj extends ServicioEntrega {
  override def prepararEntregas(entregas: List[String]): Option[List[List[Movimiento]]] = {
    Some(ServicioRutaObj.prepararRutas(entregas))
  }

  override def partirEntregas(entregas: List[List[Movimiento]]): Option[Iterator[List[List[Movimiento]]]] = {
    Some(entregas.grouped(10))
  }

  override def realizarEntrega(entregas: Iterator[List[List[Movimiento]]]): Unit = {
    OperandoArchivos.escribirArchivo("==REPORTE DE ENTREGAS==", "1")
    entregas.foreach(entrega => {
      val nuevoDron = Dron(Posicion(0,0,N()),"1", entrega)
      ejecutarDron(nuevoDron)
    })

  }

  override def ejecutarDron(dron: Dron): Try[Dron] = {
    val dronMoved = ejecutarMovimiento(dron)
    val validarDron: Try[Dron] = dronMoved.flatMap(dron => ServicioBarrioObj.validarPosicion(dron, Barrio(10,10,-10,-10)))
    val dron2 = if (validarDron.isSuccess){
      dronMoved.map(d => {
        reportarMovimiento(d)
        Dron(Posicion(d.posicion.x, d.posicion.y, d.posicion.direccion),d.file, d.entregas.tail)
      })
    }else{
      Try{dron}
    }
    dron2.flatMap(dronNu => {
      if (dronNu.entregas.nonEmpty) ejecutarDron(dronNu)
      else Try{dron}
    })
  }

  override def ejecutarMovimiento(dron: Dron): Try[Dron] = {
    val rutas = dron.entregas
    var dronMoved = dron
    rutas.headOption.foreach(ruta => ruta.foreach(r => {
      dronMoved = r match {
        case A() => ServicioDronObj.adelante(dronMoved)
        case I() => ServicioDronObj.izquierda(dronMoved)
        case D() => ServicioDronObj.derecha(dronMoved)
      }
    })
    )
    Try{dronMoved}
  }

  override def reportarMovimiento(dron: Dron)= {
    val message: String = s"(${dron.posicion.x},${dron.posicion.y}) ${dron.posicion.direccion}"
    OperandoArchivos.escribirArchivo(message, dron.file)
  }
}
