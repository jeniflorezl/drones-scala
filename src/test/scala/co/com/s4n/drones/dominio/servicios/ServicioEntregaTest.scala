package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.vo.Movimiento
import org.scalatest.FunSuite

import scala.concurrent.Future
import scala.util.Try

class ServicioEntregaTest extends FunSuite{
  test("probando dron"){
    val entregas = OperandoArchivos.leerArchivo
    /*val prepararEntregas = ServicioEntregaObj.prepararEntregas(entregas getOrElse(List.empty))
    val partirEntregas = prepararEntregas.flatMap(e => ServicioEntregaObj.partirEntregas(e))
    partirEntregas.map(entregas => ServicioEntregaObj.realizarEntrega(entregas))
    */
    entregas.map(entrega => ServicioEntregaObj.prepararEntregas(entrega)
    .map(rutas => ServicioEntregaObj.partirEntregas(rutas)
    .map(rutasDron => ServicioEntregaObj.realizarEntrega(rutasDron))))
  }

}
