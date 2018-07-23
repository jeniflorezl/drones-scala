package co.com.s4n.drones.dominio.servicios

import co.com.s4n.drones.dominio.vo.Movimiento
import org.scalatest.FunSuite

import scala.util.Try

class ServicioEntregaTest extends FunSuite{
  test("probando dron"){
    val entregas = OperandoArchivos.leerArchivo
    val prepararEntregas = ServicioEntregaObj.prepararEntregas(entregas.get)
    val partirEntregas = prepararEntregas.flatMap(e => ServicioEntregaObj.partirEntregas(e))
    partirEntregas.map(entregas => ServicioEntregaObj.realizarEntrega(entregas))
  }

}
